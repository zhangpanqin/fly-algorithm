package com.fly.algorithm.stack;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author 张攀钦
 * @date 2020-02-03-15:28
 * @description
 */
public class ArrayStackTest {
    public static void main(String[] args) throws IOException {
        ArrayStack<UserDTO> arrayStack=new ArrayStack<>();
        arrayStack.push(new UserDTO("1"));
        arrayStack.push(new UserDTO("2"));
        arrayStack.push(new UserDTO("3"));
        URL resource = ArrayStackTest.class.getResource("");
        Path path = Paths.get(resource.getPath(),"a.txt");
        File file =path.toFile();
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            file.createNewFile();
        }
        System.out.println(path.toAbsolutePath());
    }
}
