package com.heren.his.demo;

import java.util.ArrayList;
import java.util.List;


/**
 * @author hut@hf.webex.com method info including the logic for the class
 *         contructor
 */
public class MyMethodInfo {
    private int startLine;
    private int endLine;
    private String methodName = "";
    private String className = "";
    private String shortMethodName = "";
    private String methodType = "";
    private String testMethodName = "N/A";
    private String isTestMethodUpdate = "N/A";
    private String testClassName = "N/A";
    private List lineList = new ArrayList();

    /**
     * @param methodName
     *            String
     * @param startLine
     *            int
     * @param endLine
     *            int
     */
    public MyMethodInfo(String className, String methodName, String methodType,
        int startLine, int endLine) {
        this.className = className;
        this.startLine = startLine;
        this.endLine = endLine;
        this.methodName = methodName;
        this.methodType = methodType;

        //this.shortMethodName = CVSUtil.getShortMethodInfoName(methodName);
    }

    /**
     *
     * @param methodName
     */
    public MyMethodInfo(String className, String methodName) {
        this.className = className;
        this.methodName = methodName;
    }

    /**
     *
     * @return List
     */
    public List getLineList() {
        return lineList;
    }

    /**
     *
     */
    private void updateLineList() {
        int max = Integer.parseInt(lineList.get(0).toString());
        int min = Integer.parseInt(lineList.get(0).toString());

        for (int i = 0; i < lineList.size(); i++) {
            int currectLengthNumber = Integer.parseInt(lineList.get(i).toString());

            if (currectLengthNumber > max) {
                max = Integer.parseInt(lineList.get(i).toString());
            }

            if (currectLengthNumber < min) {
                min = Integer.parseInt(lineList.get(i).toString());
            }
        }

        startLine = min;
        endLine = max;
    }

    /**
     *
     * @param lineNumber int
     */
    public void addLineToLineList(int lineNumber) {
        lineList.add(Integer.valueOf(lineNumber));
        updateLineList();
    }

    /**
     * @param lineList List
     */
    public void setLineList(List lineList) {
        this.lineList = lineList;
        updateLineList();
    }

    /**
     *
     * @return String
     */
    public String getClassName() {
        return className;
    }

    /**
     * @return String
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * @return String
     */
    public String getMethodType() {
        return methodType;
    }

    /**
     * @return int
     */
    public int getStartLine() {
        return startLine;
    }

    /**
     * @return int
     */
    public int getEndLine() {
        return endLine;
    }

    /**
     *
     * @return
     */
    public String getShortMethodName() {
        return shortMethodName;
    }

    public void setTestMethodUpdate(String isTestMethodUpdate) {
        this.isTestMethodUpdate = isTestMethodUpdate;
    }

    public String getTestMethodUpdate() {
        return this.isTestMethodUpdate;
    }

    public void setTestMethodName(String testMethodName) {
        this.testMethodName = testMethodName;
    }

    public String getTestMethodName() {
        return this.testMethodName;
    }

    public void setTestClassName(String testClassName) {
        this.testClassName = testClassName;
    }

    public String getTestClassName() {
        return testClassName;
    }

    /**
     *
     * @param lineNumber
     * @return boolean
     */
    public boolean isModified(int lineNumber) {
        boolean result = false;

        if ((lineNumber <= endLine) && (lineNumber >= startLine)) {
            result = true;
        }

        return result;
    }

    /**
     * @return String
     */
    public String toString() {
        return methodName + " locate between " + startLine + " and " + endLine
        + "\n";
    }

//    public static void main(String[] args) {
//        MyMethodInfo methodinfo = new MyMethodInfo("sdfasdf", "wanglinghua");
//        methodinfo.addLineToLineList(12);
//        methodinfo.addLineToLineList(2);
//        methodinfo.addLineToLineList(4);
//        methodinfo.addLineToLineList(5);
//        methodinfo.addLineToLineList(23);
//        methodinfo.addLineToLineList(232);
//        methodinfo.addLineToLineList(43);
//        methodinfo.addLineToLineList(231);
//        methodinfo.addLineToLineList(13);
//        methodinfo.addLineToLineList(156);
//        methodinfo.addLineToLineList(2341);
//        System.out.print(methodinfo.toString());
//    }
}

