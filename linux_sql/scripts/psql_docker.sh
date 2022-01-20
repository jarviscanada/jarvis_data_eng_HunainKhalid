#!/bin/sh
# Name: Hunain Khalid
# Date: Sat, Dec, 4, 2021
# Purpose: Create automated bash script to run docker container called
#          jrvs-psql, and access the psql instance inside of it.
# Filename: psql_docker.sh

# First gain all credentials needed for full
# operation of the script, default is
# user: postgres, pass: postgres
cmd=$1
db_username=$2
db_password=$3

# Check to see if docker is running, start and run
# docker if stopped.
sudo systemctl status docker || sudo systemctl start docker

# Check container 'jrvs-psql' using inspect,
# Set variable 'container_status' to be return status
# Of command above.
docker container inspect jrvs-psql
container_status=$?

# for variable 'cmd' go to respective branch,
# else output 'invalid command'
case $cmd in
  create)
  # Check if the container already exists, exit
  # if container already created.
  if [ $container_status -eq 0 ]; then
	  echo 'Container already exists, exiting.'
	  exit 1
	fi
 # Check # of CLI arguments, needs to be 3 or
 # else prompt an exit.
  if [ $# -ne 3 ]; then
    echo 'Command "create" needs exactly 3 arguments, exiting.'
    exit 1
  fi

  # Install postgresql CLI tool on local development machine
  # (sudo for global)
  sudo yum install -y postgresql

  # Pull 'postgres' + os img through dockerhub,
  # Create docker volume 'pgdata' (new harddrive)
  # Export PGPASSWORD using db_password (entered by user)
  # Run container (init container using name: jrvs-psql, pass: user input.
  # Use volume (-v) pgdata and port (-p) 5432:5432 with Linux Alpine 9.6)
  docker pull postgres
  docker volume create pgdata
  export PGPASSWORD=$db_password
	docker run --name jrvs-psql -e POSTGRES_USER=${db_username} -e POSTGRES_PASSWORD=${db_password} -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres:9.6-alpine

  # Start the container, use return status (create_cont_sts) to see if
  # container named 'jrvs-psql' exists, if it does exit normally, else
  # state error.
  docker container start jrvs-psql
  create_cont_sts=$?

  # Detect newly created container, if exited 0 then
  # echo success, otherwise echo failure
  if [ $create_cont_sts -eq 1 ]; then
      echo "Container failed to create, exiting."
      exit 1
  fi

  # End shell script branch 'create'
	echo "Container created and started, closing container."
  echo "Use [start] to re-run container."
	exit 0
	;;

  start)
  # check instance status; exit 1 if container
  # has not been created
  if [ $container_status -eq 1 ]; then
    echo 'Container does not exist, exiting.'
    exit 1
  fi

  # start and check container 'jrvs-psql'
  docker container start jrvs-psql
  strt_cont_sts=$?

  # Detect newly created container, if exited 0 then
  # echo success, otherwise echo failure
  if [ $strt_cont_sts -eq 0 ]; then
    echo  "Container started successfully."
  fi
  if [ $strt_cont_sts -eq 1 ]; then
      echo "Container failed to start, exiting."
      exit 1
  fi

  # End shell script branch 'start'
  exit 0
  ;;

  stop)
  # check instance status; exit 1 if container
  # has not been created
  if [ $container_status -eq 1 ]; then
  	echo 'Container does not exist, exiting.'
  	exit 1
  fi

  # Otherwise stop the container
  docker container stop jrvs-psql

  # Exit with exit code of above
  echo "Container successfully stopped."
  exit $?
  ;;

  *)
	echo 'Illegal command'
	echo 'Usage, ./scripts/psql_docker.sh [create] [db_user] [db_password] or'
	echo 'Usage, ./scripts/psql_docker.sh [start] or'
	echo 'Usage, ./scripts/psql_docker.sh [stop]'
	exit 1
	;;
esac
