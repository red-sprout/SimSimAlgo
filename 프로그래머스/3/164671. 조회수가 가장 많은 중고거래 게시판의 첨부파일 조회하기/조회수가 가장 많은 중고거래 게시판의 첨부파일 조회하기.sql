SELECT CONCAT('/home/grep/src/', B.BOARD_ID, '/', F.FILE_ID, F.FILE_NAME, F.FILE_EXT) AS FILE_PATH
FROM (
    SELECT BOARD_ID
    FROM USED_GOODS_BOARD
    WHERE VIEWS = (
        SELECT MAX(VIEWS) 
        FROM USED_GOODS_BOARD
    )
) B
JOIN USED_GOODS_FILE F ON (B.BOARD_ID = F.BOARD_ID)
ORDER BY F.FILE_ID DESC