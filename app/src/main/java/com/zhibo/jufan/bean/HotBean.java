package com.zhibo.jufan.bean;

import java.util.List;

/**
 * Created by wangpenglong on 2016-10-8.
 */
public class HotBean {

    /**
     * content : {"page":{"curpage":0,"totalpage":1},"list":[{"uid":500030416,"smallheadimg":"http://resource.jufan.tv/jufan/userhead20160821/d1995c30cd1c6bf1f9b0.png","bigheadimg":"http://resource.jufan.tv/jufan/userhead20160821/a11cfbedbc59fb8cbb4f.png","score":0,"hotNo":1,"midheadimg":"http://resource.jufan.tv/jufan/userhead20160821/f516a3f0460ef787179a.png","online":913,"hotScore":4.57,"level":15,"rid":500030416,"servers":[{"port":8888,"host":"123.207.238.49"}],"name":"从此不姓乐","livename":"","place":"株洲市","video":"http://ksdownhdl.jufan.tv/live/500030416.flv"},{"uid":500140901,"smallheadimg":"http://resource.jufan.tv/jufan/userhead20161001/1382928ca373c390e74b.jpg","bigheadimg":"http://resource.jufan.tv/jufan/userhead20161001/b3bcd71b63f30d1f9d5a.jpg","score":0,"hotNo":2,"midheadimg":"http://resource.jufan.tv/jufan/userhead20161001/62f61967043d09210b88.jpg","online":510,"hotScore":0.81,"level":7,"rid":500140901,"servers":[{"port":8888,"host":"123.207.238.49"}],"name":"菜菜","livename":"早安早安，上班路上","place":"上海市","video":"http://ksdownhdl.jufan.tv/live/500140901.flv"},{"uid":88555250,"smallheadimg":"http://resource.jufan.tv/jufan/userhead20161008/afe1319fbb1cf1d5eda3.png","bigheadimg":"http://resource.jufan.tv/jufan/userhead20161008/5586156ed2abcc857a41.png","score":0,"hotNo":3,"midheadimg":"http://resource.jufan.tv/jufan/userhead20161008/d71eec1253fb972582a6.png","online":138,"hotScore":0.16,"level":17,"rid":88555250,"servers":[{"port":8888,"host":"123.207.238.49"}],"name":"不流泪的眼泪","livename":"","place":"邵阳市","video":"http://ksdownhdl.jufan.tv/live/88555250.flv"},{"uid":500252512,"smallheadimg":"http://q.qlogo.cn/qqapp/1105125329/D5C77AD9B16BB71EC059DB60B3F3E06C/100","bigheadimg":"http://q.qlogo.cn/qqapp/1105125329/D5C77AD9B16BB71EC059DB60B3F3E06C/100","score":0,"hotNo":4,"midheadimg":"http://q.qlogo.cn/qqapp/1105125329/D5C77AD9B16BB71EC059DB60B3F3E06C/100","online":74,"hotScore":0.16,"level":1,"rid":500252512,"servers":[{"port":8888,"host":"123.207.238.49"}],"name":"妳别犯贱","livename":"","place":"齐齐哈尔市","video":"http://ksdownhdl.jufan.tv/live/500252512.flv"},{"uid":500252527,"smallheadimg":"http://q.qlogo.cn/qqapp/1105125329/B58CD603DCD91D68BAE9FA7A89030480/100","bigheadimg":"http://q.qlogo.cn/qqapp/1105125329/B58CD603DCD91D68BAE9FA7A89030480/100","score":0,"hotNo":5,"midheadimg":"http://q.qlogo.cn/qqapp/1105125329/B58CD603DCD91D68BAE9FA7A89030480/100","online":153,"hotScore":0,"level":1,"rid":500252527,"servers":[{"port":8888,"host":"123.207.238.49"}],"name":"waiting for you","livename":"","place":"长春市","video":"http://ksdownhdl.jufan.tv/live/500252527.flv"}],"banner":[{"id":47,"sort":3,"rule":"","img":"http://resource.jufan.tv/jufan/ad20160930/14842714d992f42e6a20.jpg","type":1,"comment":"国庆活动","url":"http://page.jufan.tv/juNationalday/","isShow":1},{"id":48,"sort":3,"rule":"","img":"http://resource.jufan.tv/jufan/ad20160930/bc4acc6256647c4950be.jpg","type":1,"comment":"主播福利","url":"http://page.jufan.tv/node/active/fuli","isShow":1},{"id":42,"sort":2,"img":"http://resource.jufan.tv/jufan/ad20160918/5e3a12fd29db1d82e045.png","type":1,"comment":"聚范文明公约&官方客服","url":"http://page.jufan.tv/banner_list/index.html","isShow":1},{"id":44,"sort":2,"img":"http://resource.jufan.tv/jufan/ad20160923/e4d1e1909a05a0bad634.jpg","type":1,"comment":"【招募令】","url":"http://mp.weixin.qq.com/s?__biz=MzAxODkzMzc1Mg==&mid=2247484589&idx=1&sn=2519688e8b75a53285637f7c4e7b24a0&scene=0#wechat_redirect","isShow":1},{"id":9,"sort":0,"img":"http://resource.jufan.tv/jufan/ad/20160705/2a203939059ca2a7766f.png","type":1,"comment":"全民经纪人","url":"http://page.jufan.tv/juDiamondKing/broker.html","isShow":1}]}
     * state : 0
     */
    public ContentEntity content;
    public int state;

    public static class ContentEntity {
        /**
         * page : {"curpage":0,"totalpage":1}
         * list : [{"uid":500030416,"smallheadimg":"http://resource.jufan.tv/jufan/userhead20160821/d1995c30cd1c6bf1f9b0.png","bigheadimg":"http://resource.jufan.tv/jufan/userhead20160821/a11cfbedbc59fb8cbb4f.png","score":0,"hotNo":1,"midheadimg":"http://resource.jufan.tv/jufan/userhead20160821/f516a3f0460ef787179a.png","online":913,"hotScore":4.57,"level":15,"rid":500030416,"servers":[{"port":8888,"host":"123.207.238.49"}],"name":"从此不姓乐","livename":"","place":"株洲市","video":"http://ksdownhdl.jufan.tv/live/500030416.flv"},{"uid":500140901,"smallheadimg":"http://resource.jufan.tv/jufan/userhead20161001/1382928ca373c390e74b.jpg","bigheadimg":"http://resource.jufan.tv/jufan/userhead20161001/b3bcd71b63f30d1f9d5a.jpg","score":0,"hotNo":2,"midheadimg":"http://resource.jufan.tv/jufan/userhead20161001/62f61967043d09210b88.jpg","online":510,"hotScore":0.81,"level":7,"rid":500140901,"servers":[{"port":8888,"host":"123.207.238.49"}],"name":"菜菜","livename":"早安早安，上班路上","place":"上海市","video":"http://ksdownhdl.jufan.tv/live/500140901.flv"},{"uid":88555250,"smallheadimg":"http://resource.jufan.tv/jufan/userhead20161008/afe1319fbb1cf1d5eda3.png","bigheadimg":"http://resource.jufan.tv/jufan/userhead20161008/5586156ed2abcc857a41.png","score":0,"hotNo":3,"midheadimg":"http://resource.jufan.tv/jufan/userhead20161008/d71eec1253fb972582a6.png","online":138,"hotScore":0.16,"level":17,"rid":88555250,"servers":[{"port":8888,"host":"123.207.238.49"}],"name":"不流泪的眼泪","livename":"","place":"邵阳市","video":"http://ksdownhdl.jufan.tv/live/88555250.flv"},{"uid":500252512,"smallheadimg":"http://q.qlogo.cn/qqapp/1105125329/D5C77AD9B16BB71EC059DB60B3F3E06C/100","bigheadimg":"http://q.qlogo.cn/qqapp/1105125329/D5C77AD9B16BB71EC059DB60B3F3E06C/100","score":0,"hotNo":4,"midheadimg":"http://q.qlogo.cn/qqapp/1105125329/D5C77AD9B16BB71EC059DB60B3F3E06C/100","online":74,"hotScore":0.16,"level":1,"rid":500252512,"servers":[{"port":8888,"host":"123.207.238.49"}],"name":"妳别犯贱","livename":"","place":"齐齐哈尔市","video":"http://ksdownhdl.jufan.tv/live/500252512.flv"},{"uid":500252527,"smallheadimg":"http://q.qlogo.cn/qqapp/1105125329/B58CD603DCD91D68BAE9FA7A89030480/100","bigheadimg":"http://q.qlogo.cn/qqapp/1105125329/B58CD603DCD91D68BAE9FA7A89030480/100","score":0,"hotNo":5,"midheadimg":"http://q.qlogo.cn/qqapp/1105125329/B58CD603DCD91D68BAE9FA7A89030480/100","online":153,"hotScore":0,"level":1,"rid":500252527,"servers":[{"port":8888,"host":"123.207.238.49"}],"name":"waiting for you","livename":"","place":"长春市","video":"http://ksdownhdl.jufan.tv/live/500252527.flv"}]
         * banner : [{"id":47,"sort":3,"rule":"","img":"http://resource.jufan.tv/jufan/ad20160930/14842714d992f42e6a20.jpg","type":1,"comment":"国庆活动","url":"http://page.jufan.tv/juNationalday/","isShow":1},{"id":48,"sort":3,"rule":"","img":"http://resource.jufan.tv/jufan/ad20160930/bc4acc6256647c4950be.jpg","type":1,"comment":"主播福利","url":"http://page.jufan.tv/node/active/fuli","isShow":1},{"id":42,"sort":2,"img":"http://resource.jufan.tv/jufan/ad20160918/5e3a12fd29db1d82e045.png","type":1,"comment":"聚范文明公约&官方客服","url":"http://page.jufan.tv/banner_list/index.html","isShow":1},{"id":44,"sort":2,"img":"http://resource.jufan.tv/jufan/ad20160923/e4d1e1909a05a0bad634.jpg","type":1,"comment":"【招募令】","url":"http://mp.weixin.qq.com/s?__biz=MzAxODkzMzc1Mg==&mid=2247484589&idx=1&sn=2519688e8b75a53285637f7c4e7b24a0&scene=0#wechat_redirect","isShow":1},{"id":9,"sort":0,"img":"http://resource.jufan.tv/jufan/ad/20160705/2a203939059ca2a7766f.png","type":1,"comment":"全民经纪人","url":"http://page.jufan.tv/juDiamondKing/broker.html","isShow":1}]
         */
        public PageEntity page;
        public List<ListEntity> list;
        public List<BannerEntity> banner;

        public static class PageEntity {
            /**
             * curpage : 0
             * totalpage : 1
             */
            public int curpage;
            public int totalpage;
        }

        public static class ListEntity {
            /**
             * uid : 500030416
             * smallheadimg : http://resource.jufan.tv/jufan/userhead20160821/d1995c30cd1c6bf1f9b0.png
             * bigheadimg : http://resource.jufan.tv/jufan/userhead20160821/a11cfbedbc59fb8cbb4f.png
             * score : 0.0
             * hotNo : 1
             * midheadimg : http://resource.jufan.tv/jufan/userhead20160821/f516a3f0460ef787179a.png
             * online : 913
             * hotScore : 4.57
             * level : 15
             * rid : 500030416
             * servers : [{"port":8888,"host":"123.207.238.49"}]
             * name : 从此不姓乐
             * livename :
             * place : 株洲市
             * video : http://ksdownhdl.jufan.tv/live/500030416.flv
             */
            public int uid;
            public String smallheadimg;
            public String bigheadimg;
            public double score;
            public int hotNo;
            public String midheadimg;
            public int online;
            public double hotScore;
            public int level;
            public int rid;
            public List<ServersEntity> servers;
            public String name;
            public String livename;
            public String place;
            public String video;

            public static class ServersEntity {
                /**
                 * port : 8888
                 * host : 123.207.238.49
                 */
                public int port;
                public String host;
            }
        }

        public static class BannerEntity {
            /**
             * id : 47
             * sort : 3
             * rule :
             * img : http://resource.jufan.tv/jufan/ad20160930/14842714d992f42e6a20.jpg
             * type : 1
             * comment : 国庆活动
             * url : http://page.jufan.tv/juNationalday/
             * isShow : 1
             */
            public int id;
            public int sort;
            public String rule;
            public String img;
            public int type;
            public String comment;
            public String url;
            public int isShow;
        }
    }
}
