SELECT ID,
    CASE
        WHEN RANK_PCT <= 0.25 THEN 'CRITICAL'
        WHEN RANK_PCT <= 0.5 THEN 'HIGH'
        WHEN RANK_PCT <= 0.75 THEN 'MEDIUM'
        ELSE 'LOW'
    END AS COLONY_NAME
FROM (
    SELECT ID,
        SIZE_OF_COLONY,
        PERCENT_RANK() OVER (ORDER BY SIZE_OF_COLONY DESC) AS RANK_PCT
    FROM ECOLI_DATA) RNAKED_DATA
ORDER BY ID ASC
        