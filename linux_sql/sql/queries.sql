-- you can also create a function for convenience purposes so your qeury looks cleaner
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
$$
BEGIN
    RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
    LANGUAGE PLPGSQL;

-- query 1
SELECT cpu_number, id, total_mem
from host_info
ORDER BY cpu_number, (total_mem) DESC;

-- query 2
select hu.host_id, hi.hostname, round5(hu.timestamp) as interval_5min,
(
	AVG
		(
		(hi.total_mem - hu.memory_free) / hi.total_mem
		)
* 100
) as mem_avg_percent
from host_info hi , host_usage hu
where hi.id = hu.host_id
group by round5(hu.timestamp), host_id, hostname
order by hu.host_id, round5(hu.timestamp);

-- query 3
select host_id, round5(timestamp) as interval_5min, count(*) as data_points
from host_usage
group by host_id, interval_5min
having count(*) < 3
order by interval_5min;