ALTER TABLE TEST_CASE ADD SAVE_PHOTO NUMBER(1);

COMMENT ON COLUMN TEST_CASE.SAVE_PHOTO IS '是否存储截图，1为存储';