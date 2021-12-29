#!/bin/bash

# Exit if arguments are not exactly 5
if [ "$#" -ne 5 ]; then
    echo 'Error, did not give exactly 5 parameters.'
    exit 1
fi

# Save output from running programs below,
# into respective variables
lscpu_out=`lscpu`
mem_info_out=`cat /proc/meminfo`
vmstat_info=`vmstat -t`

# Setup variables to be used by script
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

# Save needed information into respective variables,
# for insertion purposes into database named 'host_agent'
hostname=$(hostname -f)
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out"  | egrep "Model:" | awk '{print $2}' | xargs)
cpu_mhz=$(echo "$lscpu_out"  | egrep "^CPU MHz:" | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out"  | egrep "L2 cache:" | awk '{print substr($3,1,length($3)-1)}' | xargs)
total_mem=$(echo "$mem_info_out"  | egrep "MemTotal:" | awk '{print $2}' | xargs)
timestamp=$(echo "$vmstat_info" | awk 'NR == 3 {print $18 " "$19}' | xargs)

# Insert data into table, host_info using data below
insert_data="INSERT INTO host_info (hostname,
cpu_number, cpu_architecture, cpu_model
, L2_cache, total_mem, timestamp) VALUES
('$hostname', $cpu_number, '$cpu_architecture',
'$cpu_model', $cpu_mhz, $l2_cache, $total_mem, '$timestamp')"

export PGPASSWORD=$psql_password
#Insert data into a database, using psql instance
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_data"
exit $?