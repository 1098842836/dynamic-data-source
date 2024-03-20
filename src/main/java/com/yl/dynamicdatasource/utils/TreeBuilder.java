package com.yl.dynamicdatasource.utils;

import java.util.*;

public class TreeBuilder {

    private static String CHILDREN_KEY = "children";


    /**
     * 数据为数组集合，数据大致这样 select ne.id   as neid,ne.name as nename,vm.id   as vmid,vm.name as vmname from ，，，，，，
     * neid和nename为父类，vmid和vmname为子类，还可以设置孙子类等等无线往下拓展
     * @param itemList 目标数据稽核
     * @param config 父-子-孙子等的配置文件
     * @return
     */
    public static List<Map<String, Object>> buildTree(List<Map> itemList, TreeLevelConfig config) {

        Set<Object> set = new HashSet<>();
        Map allMap = new HashMap<>();
        List<Map<String, Object>> treeList = new ArrayList<>();
        String rootIdKey = config.getIdKey();
        String rootNameKey = config.getNameKey();

        for (Map<String, Object> item : itemList) {
            Map<String, Object> currentMap;
            Object rootId = item.get(rootIdKey);
            Object rootName = item.get(rootNameKey);
            if (!set.contains(rootId)) {
                currentMap = new HashMap<>();
                currentMap.put("id", rootId);
                currentMap.put("name", rootName);
                allMap.put(rootId, currentMap);
                treeList.add(currentMap);
                set.add(rootId);
            } else {
                currentMap = (Map<String, Object>) allMap.get(rootId);
            }
            setVal(config.getChildren(), item, currentMap, allMap, set);
        }
        return treeList;
    }

    private static void setVal(TreeLevelConfig config,
                               Map<String, Object> item,
                               Map currentMap,
                               Map allMap,
                               Set<Object> set) {
        if (config == null) {
            return;
        }
        List<Map> child;
        String idKey = config.getIdKey();
        String nameKey = config.getNameKey();
        if (currentMap.containsKey(CHILDREN_KEY)) {
            child = (List) currentMap.get(CHILDREN_KEY);
        } else {
            child = new ArrayList<>();
            currentMap.put(CHILDREN_KEY, child);
        }

        Map newMap;
        if (!set.contains(item.get(idKey))) {
            newMap = new HashMap<>();
            newMap.put("id", item.get(idKey));
            newMap.put("name", item.get(nameKey));
            child.add(newMap);
            allMap.put(newMap.get("id"), newMap);
            set.add(newMap.get("id"));
        } else {
            newMap = (Map) allMap.get(item.get(idKey));
        }
        if (config.getChildren() != null) {
            setVal(config.getChildren(), item, newMap, allMap, set);
        }

    }


    public static TreeLevelConfig config(String idKey, String nameKey) {
        TreeBuilder treeBuilder = new TreeBuilder();
        TreeLevelConfig treeLevelConfig = treeBuilder.new TreeLevelConfig(idKey, nameKey);
        return treeLevelConfig;
    }


    public class TreeLevelConfig {
        private String idKey;

        private String nameKey;

        private Integer level;

        private TreeLevelConfig children;


        public TreeLevelConfig(final String idKey, final String nameKey) {
            this.idKey = idKey;
            this.nameKey = nameKey;
        }

        public TreeLevelConfig() {
        }

        public TreeLevelConfig addChild(String idKey, String nameKey) {
            if (this.children == null) {
                this.children = new TreeLevelConfig(idKey, nameKey);
            } else {
                this.children.addChild(idKey, nameKey);
            }
            return this;
        }

        public String getIdKey() {
            return this.idKey;
        }

        public void setIdKey(final String idKey) {
            this.idKey = idKey;
        }

        public String getNameKey() {
            return this.nameKey;
        }

        public void setNameKey(final String nameKey) {
            this.nameKey = nameKey;
        }

        public Integer getLevel() {
            return this.level;
        }

        public void setLevel(final Integer level) {
            this.level = level;
        }

        public TreeLevelConfig getChildren() {
            return this.children;
        }

        public void setChildren(final TreeLevelConfig children) {
            this.children = children;
        }
    }

}
