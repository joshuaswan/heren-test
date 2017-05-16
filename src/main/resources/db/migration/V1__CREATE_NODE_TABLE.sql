--管理对应项目以及对应分支的节点管理器
CREATE TABLE NODES_HIERARCHY (
    ID VARCHAR2(40) NOT NULL,
    NAME VARCHAR2(100),
    DETAILS VARCHAR2(1000),
    PARENT_ID  VARCHAR2(40),
    INPUT_CODE VARCHAR2(50),
    NODE_TYPE_ID  NUMBER(4),
    NODE_ORDER  NUMBER(4)
);
ALTER TABLE NODES_HIERARCHY ADD CONSTRAINT PK_NODES_HIERARCHY PRIMARY KEY (ID);

COMMENT ON TABLE NODES_HIERARCHY is '节点';
COMMENT ON COLUMN NODES_HIERARCHY.ID is '节点ID';
COMMENT ON COLUMN NODES_HIERARCHY.NAME is '节点名称';
COMMENT ON COLUMN NODES_HIERARCHY.DETAILS is '节点描述';
COMMENT ON COLUMN NODES_HIERARCHY.PARENT_ID is '父节点ID';
COMMENT ON COLUMN NODES_HIERARCHY.INPUT_CODE IS '节点输入码';
COMMENT ON COLUMN NODES_HIERARCHY.NODE_TYPE_ID is '节点类型';
COMMENT ON COLUMN NODES_HIERARCHY.NODE_ORDER is '节点序号';

-- 节点分类，对应项目、项目子节点、测试用例、测试步骤
CREATE TABLE NODE_TYPE (
  ID NUMBER(4) NOT NULL,
  DESCRIPTION VARCHAR2(100)
);

COMMENT ON TABLE NODE_TYPE IS '节点类别';
COMMENT ON COLUMN NODE_TYPE.ID IS '节点类别ID';
COMMENT ON COLUMN NODE_TYPE.DESCRIPTION IS '节点描述';

INSERT INTO NODE_TYPE (ID, DESCRIPTION) VALUES('1', 'test_project');
INSERT INTO NODE_TYPE (ID, DESCRIPTION) VALUES('2', 'test_suite');
INSERT INTO NODE_TYPE (ID, DESCRIPTION) VALUES('3', 'testcase');
INSERT INTO NODE_TYPE (ID, DESCRIPTION) VALUES('4', 'testcase_step');