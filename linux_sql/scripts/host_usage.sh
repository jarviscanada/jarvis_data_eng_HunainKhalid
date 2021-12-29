#!/bin/sh
# Name: Hunain Khalid
# Date: Sat, Dec, 11, 2021
# Purpose: Create automated bash script to attain correct
#          values per instance for linux cluster monitoring
# Filename: host_usage.sh

# Exit if arguments are not exactly 5
if [ "$#" -ne 5 ]; then
    echo 'Error, did not give exactly 5 parameters.'
    exit 1
fi

# Outputs of Setup cmds to be stored in respective variables.
# Need virtual machine stats in mb.
mem_info_out=`cat /proc/meminfo`
vmstat_info=`vmstat -t`
vmstat_d_io=`vmstat -d`
df_stat=`df -BM /`

# Setup variables to be used by script
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

# Save needed information into respective variables,
# for insertion purposes into database named 'host_agent'
hostname=$(hostname -f)
timestamp=$(echo "$vmstat_info" | awk 'NR == 3 {print $18" "$19}' | xargs)
memory_free=$(echo "$mem_info_out"  | egrep "MemFree:" | awk '{print $2}' | xargs)
cpu_idle=$(echo "$vmstat_info" | awk 'NR == 3 {print $15}'| tail -n1 |  xargs)
cpu_kernel=$(echo "$vmstat_info" | awk 'NR == 3 {print $14}'| xargs)
disk_io=$(echo "$vmstat_d_io" | awk 'NR == 3 {print $10}'| xargs)
disk_available=$(echo "$df_stat" | awk 'NR == 2 {print substr($4,1,length($4)-1)}' | xargs)

# Needed subquery to find id of host
host_id="(SELECT id FROM host_info WHERE hostname='$hostname')";

# Insert data into table, host_usage using data below
insert_data="INSERT INTO host_usage (timestamp, host_id, memory_free, cpu_idle,
cpu_kernel, disk_io, disk_available) VALUES ('$timestamp', $host_id,
$memory_free, '$cpu_idle', '$cpu_kernel', $disk_io ,$disk_available)"

export PGPASSWORD=$psql_password
#Insert data into a database, using psql instance
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_data"
exit $?