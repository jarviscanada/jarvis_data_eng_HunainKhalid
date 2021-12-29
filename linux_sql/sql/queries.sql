-- you can also create a function for convenience purposes so your qeury looks cleaner
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
$$
BEGIN
    RETURN date_trunc('hour', ts) +
    date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
    LANGUAGE PLPGSQL;

-- query 1
SELECT cpu_number, id, total_mem
FROM host_info
ORDER BY cpu_number, (total_mem) DESC;

-- query 2
SELECT hu.host_id, hi.hostname,
round5(hu.timestamp) AS interval_5min,
(AVG((hi.total_mem - hu.memory_free) / hi.total_mem)* 100)
AS mem_avg_percent
FROM host_info hi , host_usage hu
WHERE hi.id = hu.host_id
GROUP BY round5(hu.timestamp), host_id, hostname
ORDER BY hu.host_id, round5(hu.timestamp);

-- query 3
SELECT host_id, round5(timestamp) AS interval_5min, COUNT(*) AS data_points
FROM host_usage
GROUP BY host_id, interval_5min
HAVING COUNT(*) < 3
ORDER BY interval_5min;