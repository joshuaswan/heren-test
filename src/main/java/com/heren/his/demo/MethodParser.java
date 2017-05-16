/**
 *
 */
package com.heren.his.demo;

import java.util.ArrayList;
import java.util.List;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LineNumberAttribute;
import javassist.bytecode.MethodInfo;


/**
 * @author hut
 *
 */
public class MethodParser {
    /**
     *
     * @param args String[]
     * @throws NotFoundException
     */
    public static List parse(String packageName) throws NotFoundException {
        List result = new ArrayList();
        ClassPool pool = ClassPool.getDefault();

        CtClass cc = pool.get(packageName);
        CtMethod[] ctMethods = cc.getDeclaredMethods();

        for (int i = 0; i < ctMethods.length; i++) {
            MethodInfo tempCtMethodInfo = ctMethods[i].getMethodInfo();
            CodeAttribute tempCodeAttribute = tempCtMethodInfo.getCodeAttribute();

            if (tempCodeAttribute != null) {
                LineNumberAttribute tempAttributeInfo = (LineNumberAttribute) tempCodeAttribute
                    .getAttribute("LineNumberTable");

                if (tempAttributeInfo != null) {
                    MyMethodInfo myMethodInfo = new MyMethodInfo(ctMethods[i].getDeclaringClass()
                                                                             .getName(),
                            ctMethods[i].getLongName());
                    int tableLength = tempAttributeInfo.tableLength();

                    for (int j = 0; j < tableLength; j++) {
                        int tempLineNumber = tempAttributeInfo.lineNumber(j);
                        myMethodInfo.addLineToLineList(tempLineNumber);
                    }

                    result.add(myMethodInfo);
                }
            }
        }

        return result;
    }

    /**
     *
     *
     */
    public static void getResult(String packageName) {
        System.out.println("Analyzing " + packageName + " ...");

        List result;

        try {
            result = parse(packageName);

            for (int i = 0; i < result.size(); i++) {
                Object temp = result.get(i);
                System.out.println(temp.toString());
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args String[]
     */
//    public static void main(String[] args) {
//        getResult("com.heren.his.demo.MyMethodInfo");
//    }
}

