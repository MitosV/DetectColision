package com.mitosv.detectcolision.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mitosv.detectcolision.DetectColision;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigManager {

    private File file;

    private static ConfigManager instance;
    private Config currentConfig;
    private Gson gson;

    public ConfigManager(File file){
        instance = this;
        this.file = file;
        if(!file.exists())
            createFile();
        reloadConfig();
    }


    private void reloadConfig(){
        try {
            gson = new GsonBuilder().setPrettyPrinting().create();
            FileReader reader = new FileReader(this.file);
            currentConfig = gson.fromJson(reader, Config.class);
            reader.close();
        }catch (Exception e){
            DetectColision.LOGGER.error(e.getMessage());
        }
    }

    public Config getCurrentConfig(){
        try {
            gson = new GsonBuilder().setPrettyPrinting().create();
            FileReader reader = new FileReader(this.file);
            Config config = gson.fromJson(reader, Config.class);
            reader.close();
            return config;
        }catch (Exception e){
            DetectColision.LOGGER.error(e.getMessage());
            return null;
        }
    }

    private void createFile(){
        try {
            gson = new GsonBuilder().setPrettyPrinting().create();
            Config defaultConfig = new Config(64,60,10000);
            FileWriter writer = new FileWriter(file);
            gson.toJson(defaultConfig,writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConfigManager getInstance() {
        return instance;
    }

    public static class Config{
        private int maxY,minY,maxBlocks;
        public Config(int maxY, int minY, int maxBlocks){
            this.maxY = maxY;
            this.minY = minY;
            this.maxBlocks = maxBlocks;
        }

        public void setMaxY(int maxY) {
            this.maxY = maxY;
        }

        public void setMinY(int minY) {
            this.minY = minY;
        }

        public void setMaxBlocks(int maxBlocks) {
            this.maxBlocks = maxBlocks;
        }

        public int getMaxY() {
            return maxY;
        }

        public int getMinY() {
            return minY;
        }

        public int getMaxBlocks() {
            return maxBlocks;
        }
    }

}
