package com.powei.testmybatis;

import com.powei.testmybatis.controller.BorrowController;
import com.powei.testmybatis.controller.LoginController;
import com.powei.testmybatis.controller.ReaderController;
import com.powei.testmybatis.mapper.*;
import com.powei.testmybatis.pojo.*;
import com.powei.testmybatis.service.impl.BorrowService;
import com.powei.testmybatis.service.impl.ReaderService;
import com.powei.testmybatis.utils.DateUtils;
import com.powei.testmybatis.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest//单元测试整合注解
class TestMybatisApplicationTests {

//    @Test
//    void contextLoads() {
//    }
//
//
//    @Autowired
//    private AdminMapper adminMapper;
//
//    @Test
//    public void testListAdmin(){
//        List<Admin> adminList = adminMapper.getAllAdmin();
//        for (Admin admin : adminList)
//        {
//            System.out.println(admin.toString());
//        }
//    }
//
//    @Test
//    public void testGetAdminById(){
//        System.out.println("success");
//        System.out.println(adminMapper.deleteAdminById(1).toString());
//    }
//
//    @Test
//    public void testAddAdmin(){
//        Admin admin = new Admin(null,"vandarkhlome","DDF");
//        adminMapper.insertAdmin(admin);
//        System.out.println(admin.toString());
//    }
//
//    @Test
//    public void testUpdateAdmin(){
//        Admin admin = new Admin(5,"礼堂顶针","");
//    }
//
//
//    @Autowired
//    private PurViewMapper purViewMapper;
//    @Autowired
//    private ApplicationArguments springApplicationArguments;
//
//    //
//    @Test
//    public void testInsertPurView(){
//        PurView purView = new PurView(
//                null, 2,true, true, true, true, true
//        );
//        Integer purViewID = purViewMapper.insertPurView(purView);
//        purView.setId(purViewID);
//        System.out.println(purView.toString());
//    }
//
//    @Test
//    public void testGetPurViewByID(){
//        PurView purView = purViewMapper.getPurViewById(2);
//        System.out.println("-------------------");
//        System.out.println(purView.toString());
//    }
//
//    @Test
//    public void testDeleteAdminByIDs(){
//        List<Integer> ids = Arrays.asList(4,5,6,7,8,9);
//        adminMapper.deleteAdminByIDs(ids);
//    }
//
//    @Autowired
//    private BookMapper bookMapper;
//
//    @Test
//    public void testAddBook(){
//        Book testBook = new Book();
//        for (int i = 0; i < 100; i++) {
//            testBook.setBookName("天龙"+i+"部");
//            testBook.setAuthor("金庸");
//            testBook.setDel(0);
//            testBook.setPress("门头沟大学出版社");
//            testBook.setTypeId(9);
//            testBook.setId(bookMapper.addBook(testBook));
//            System.out.println(testBook.toString());
//        }
//    }
//
//    @Test
//    public void testGetBookByParas(){
//        //select all
//        BookQueryArgs args = new BookQueryArgs();
//        args.setAuthor("银庸");
//        List<Book> bookList = bookMapper.query(args);
//        for (Book book:bookList){
//            System.out.println(book.toString());
//        }
//    }
//
//    @Test
//    public void deleteAllBooks(){
//        for (int i = 0; i < 111; i++) {
//            bookMapper.permanentlyDeleteBook(i);
//        }
//    }
//
//    @Test
//    public void testUpdateBook(){
//        for (int i = 123; i < 129; i++){
//            Book book = new Book();
//            book.setId(i);
//            book.setBookName("天龙"+"⑧"+"部");
//            book.setAuthor("银庸");
//            book.setPress("重庆大学出版社");
//            book.setTypeId(9);
//            bookMapper.update(book);
//        }
//    }
//
//    @Autowired
//    private PurViewService purViewService;
//
//    @Test
//    public void testGetAuth() {
//        List<Authority> authors = purViewService.authorityList();
//        System.out.println(authors.toString());
//    }
//
//    @Test
//    public void testJWTGeneration(){
//        Map<String,Object> claims = new HashMap<>();
//        claims.put("username", "理塘丁真");
//        claims.put("password", "relx5gen");
//        claims.put("id", 1);
//        String jwt = Jwts.builder()
//                .signWith(SignatureAlgorithm.HS256, "powei")
//                .setClaims(claims)
//                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
//                .compact();
//        System.out.println(jwt);
//        //This is not related to spring framework
//    }
//
//    @Test
//    public void parseJwt(){
//        Claims claims = Jwts.parser()
//                .setSigningKey("powei")
//                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6InJlbHg1Z2VuIiwiaWQiOjEsImV4cCI6MTcxOTk4OTAwMSw" +
//                        "idXNlcm5hbWUiOiLnkIbloZjkuIHnnJ8ifQ.vfY-8SRjSxhapB1g0RZz0XlSl4avsVHeambHcGLsm3o")
//                .getBody();
//        System.out.println(claims);
//    }
//
//    @Autowired
//    BorrowMapper borrowMapper;
//    @Autowired
//    private BookMapper bookMapper;
//
//    @Test
//    public void testborrowMapper(){
//        BorrowQueryArgs arg = new BorrowQueryArgs();
//        arg.setFromTime(DateUtils.generateDate(2024,1,1));
//        arg.setToTime(DateUtils.generateDate(2024,12,12));
//        borrowMapper.query(arg);
//    }
//
//    @Test
//    public void testBorrowBook() {
//        Borrow borrow = new Borrow(null, 1,
//                1, null, null, "UNITTEST", null);
//        borrowMapper.insertBorrow(borrow);
//        Borrow tempBrr = new Borrow();
//        for (int i = 7; i < 12; i++) {
//            tempBrr.setId(i);
//            borrowMapper.returnBorrow(tempBrr);
//        }
//
//    }
//
//    @Autowired
//    private PurViewMapper purViewMapper;
//    @Test
//    public void testGetPurview() {
//        PurView purView = purViewMapper.getPurViewById(1);
//        System.out.println(purView);
//    }

    @Autowired
    private ReaderMapper readerMapper;
//    @Autowired
//    private ;
    @Autowired
    private LoginController loginController;
    @Autowired
    private ReaderController readerController;
    @Autowired
    private BorrowController borrowController;

    @Test
    public void testReaderservice(){
        Reader reader = new Reader();
        reader.setName("readerUnitTest");
        reader.setEmail("test@outlook.com");
        reader.setExpireDate(DateUtils.generateDate(2024, 12, 12));
//        readerMapper.insert(reader);
        reader.setReaderId(1);
        reader.setTypeId(1);
        readerMapper.update(reader);
        return;
    }

    @Test
    public void testChara(){
        Admin admin = new Admin();
        admin.setId(47);
        admin.setPassword("admin");
        admin.setName("admin");
        String token = (String) loginController.login(admin).getData();
        System.out.println(token);


        Chara chara = new Chara();
        chara.setName("Student");
        chara.setDuration(99);
        chara.setMaxBorrow(5);
        readerController.addChara(chara, token);
        chara.setName("Professor");
        chara.setDuration(1000);
        chara.setMaxBorrow(100);
        readerController.addChara(chara, token);
//        readerController.updateChara(chara, token);
//        readerController.deleteChara(1, token);
    }

    @Test
    public void testBorrow(){
        Admin admin = new Admin();
        admin.setId(47);
        admin.setPassword("admin");
        admin.setName("admian");
        String token = (String) loginController.login(admin).getData();
        System.out.println(token);

        Borrow borrow = new Borrow();
        borrow.setReaderId(1);
        borrow.setBookId(108);
        borrow.setOperator("UnitTest");

        Result res = borrowController.borrowBook(borrow,1,token);
        System.out.println(res.toString());
    }

    @Test
    public void testReturn(){
        Admin admin = new Admin();
        admin.setId(47);
        admin.setPassword("admin");
        admin.setName("admin");
        String token = (String) loginController.login(admin).getData();
        System.out.println(token);

//        Borrow borrow = new Borrow();
//        borrow.setReaderId(1);
//        borrow.setBookId(108);
//        borrow.setOperator("UnitTest");


        BorrowQueryArgs borrowQueryArgs = new BorrowQueryArgs();
        borrowQueryArgs.setReaderId(1);
        List<Borrow> borrowLogs = (List<Borrow>) borrowController.queryBorrow(borrowQueryArgs,token).getData();
        System.out.println(borrowLogs.toString());
        for (Borrow borrowlog:borrowLogs) {
            Result res = borrowController.returnBook(borrowlog, token);
            System.out.println(res.toString());
        }
    }

    @Autowired
    BookMapper bookMapper;
    @Test
    public void testBook(){
        String[] press = {"门头沟大学出版社", "重庆大学出版社", "机械工业出版社", "中国社会科学出版社", "中国建筑工业出版社", "中国人民出版社"};
        String[] authors = {"丁真", "科比", "佐巴扬", "余胜军", "Linus", "习近平", "Alen Turing",
        "刘慈欣", "周志华", "吴恩达", "黄仁勋", "谷爱凌"};
        String[] bookNames = {
                "计算机程序设计艺术",
                "编程珠玑",
                "计算机组成与设计：硬件/软件接口",
                "计算机网络：自顶向下方法",
                "计算机系统结构",
                "计算机视觉",
                "计算机图形学",
                "并行与分布式计算",
                "信息安全",
                "数据挖掘导论",
                "人机交互",
                "软件工程",
                "信息检索",
                "计算机游戏设计与技术",
                "计算机音乐",
                "算法设计",
                "编译器设计",
                "高性能计算",
                "计算机体系结构",
                "自然语言处理",
                "数字图像处理",
                "计算机视觉算法与应用",
                "机器学习实战",
                "深度学习入门",
                "神经网络与深度学习",
                "Python编程：从入门到实践",
                "Python数据分析",
                "Python机器学习",
                "Python深度学习",
                "Python网络编程",
                "Python爬虫开发与案例分析",
                "Python游戏编程快速上手",
                "Java核心技术",
                "Java并发编程实战",
                "Java网络编程",
                "Java设计模式",
                "Java EE企业级应用开发",
                "JavaScript高级程序设计",
                "JavaScript ES6新特性",
                "React进阶之路",
                "Vue.js实战",
                "Angular权威指南",
                "Node.js实战",
                "HTML5与CSS3基础教程",
                "Web前端开发",
                "Web全栈工程师手册",
                "Web性能优化",
                "区块链技术原理与开发实践",
                "人工智能：一种现代的方法",
                "智能时代",
                "大数据技术原理与应用",
                "数据科学导论",
                "数据可视化",
                "算法图解",
                "计算机科学导论",
                "计算机科学史",
                "黑客与画家",
                "硅谷之谜",
                "乔布斯传",
                "极客与团队",
                "创业维艰",
                "硅谷钢铁侠：埃隆·马斯克的冒险人生",
                "硅谷秘史",
                "创新者的窘境",
                "创新者的解答",
                "未来简史",
                "人类简史",
                "21世纪资本论",
                "乌合之众",
                "枪炮、病菌与钢铁",
                "黑天鹅",
                "思考快与慢",
                "原则",
                "穷查理宝典",
                "影响力",
                "自私的基因",
                "社会契约论",
                "乌托邦",
                "理想国",
                "君主论",
                "人性的弱点",
                "人性的优点",
                "国富论",
                "资本论",
                "战争论",
                "道德经",
                "孙子兵法",
                "易经",
                "诗经",
                "楚辞",
                "论语",
                "孟子",
                "庄子",
                "墨子",
                "史记",
                "资治通鉴",
                "三国演义",
                "水浒传",
                "西游记",
                "红楼梦",
                "儒林外史",
                "聊斋志异",
                "梦溪笔谈",
                "山海经",
                "世说新语",
                "唐诗三百首",
                "宋词精选",
                "元曲选",
                "明史",
                "清史稿",
                "鲁迅全集",
                "茅盾文集",
                "老舍小说集",
                "巴金散文集",
                "钱钟书文集",
                "沈从文散文",
                "张爱玲小说",
                "王小波文集",
                "余华小说集",
                "莫言小说选",
                "贾平凹散文",
                "韩寒作品集",
                "郭敬明小说",
                "三体",
                "球状闪电",
                "超新星纪元",
                "流浪地球",
                "死神永生",
                "地球往事",
                "三体问题",
                "时间移民",
                "吞食者",
                "朝闻道",
                "微纪元",
                "诗云",
                "乡村教师",
                "镜子",
                "梦之海",
                "魔鬼积木",
                "流浪地球Ⅱ",
                "地球大炮",
                "全频带阻塞干扰",
                "赡养人类",
                "中国2185",
                "光荣与梦想",
                "黄金原野",
                "吞食帝国",
                "人和吞食者",
                "欢乐颂",
                "三体Ⅲ：死神永生",
                "时间之外的往事",
                "三体Ⅱ：黑暗森林",
                "天父地母",
                "宇宙坍缩",
                "超新星纪元",
                "地球往事",
                "超新星纪元",
                "超时空接触",
                "基地系列",
                "沙丘",
                "星际迷航",
                "银河帝国",
                "火星三部曲",
                "时间机器",
                "世界大战",
                "海底两万里",
                "神秘岛",
                "八十天环游地球",
                "气球上的五星期",
                "格兰特船长的儿女",
                "地心游记",
                "环游月球",
                "太阳系历险记",
                "八十天环游世界",
                "海底两万里",
                "地心之旅",
                "从地球到月球",
                "神秘岛",
                "太阳系的奇迹",
                "环游月球",
                "海底的秘密",
                "气球上的五星期",
                "海底两万里",
                "地心游记",
                "神秘岛",
                "从地球到月球",
                "八十天环游世界",
                "环游月球",
                "太阳系的奇迹",
                "地心之旅",
                "气球上的五星期",
                "海底的秘密",
                "太阳系历险记",
                "八十天环游地球",
                "气球上的五星期",
                "格兰特船长的儿女",
                "地心游记",
                "环游月球",
                "海底两万里",
                "神秘岛",
                "太阳系历险记",
                "环游月球",
                "地心游记",
                "气球上的五星期",
                "海底的秘密",
                "太阳系的奇迹",
                "八十天环游地球",
                "地心之旅",
                "从地球到月球",
                "神秘岛",
                "环游月球",
                "海底两万里",
                "太阳系历险记",
                "地心游记",
                "气球上的五星期",
                "海底的秘密",
                "太阳系的奇迹",
                "八十天环游地球",
                "地心之旅",
                "从地球到月球",
                "神秘岛",
                "环游月球",
                "海底两万里",
                "太阳系历险记",
                "地心游记",
                "气球上的五星期",
                "海底的秘密",
                "太阳系的奇迹",
                "八十天环游地球",
                "地心之旅",
                "从地球到月球",
                "进击的巨人",
                "火影忍者",
                "海贼王",
                "龙珠",
                "鬼灭之刃",
                "一拳超人",
                "名侦探柯南",
                "钢之炼金术师",
                "东京食尸鬼",
                "JoJo的奇妙冒险",
                "死神",
                "犬夜叉",
                "银魂",
                "排球少年",
                "游戏王",
                "新世纪福音战士",
                "机动战士高达",
                "魔法禁书目录",
                "黑子的篮球",
                "刀剑神域",
                "我的英雄学院",
                "全职猎人",
                "七龙珠超",
                "斩服少女",
                "钢之男",
                "妖精的尾巴",
                "魔法少女小圆",
                "东京暗鸦",
                "进击！巨人中学生",
                "未来日记",
                "空之境界",
                "钢壳都市雷吉欧斯",
                "叛逆的鲁路修",
                "黑执事",
                "斩·赤红之瞳",
                "进击的巨人Before the Fall",
                "恶魔高校DxD",
                "Re:从零开始的异世界生活",
                "路人女主的养成方法",
                "Overlord",
                "关于我转生变成史莱姆这档事",
                "异世界魔王与召唤少女的奴隶魔术",
                "魔王勇者",
                "零之使魔",
                "魔法科高中的劣等生",
                "加速世界",
                "罪恶王冠",
                "魔法老师",
                "守护甜心",
                "精灵宝可梦",
                "数码宝贝",
                "天元突破红莲螺岩",
                "宇宙战舰大和号",
                "幸运星",
                "偶像大师",
                "偶像活动",
                "Love Live!",
                "偶像梦幻祭",
                "BanG Dream!",
                "辉夜大小姐想让我告白",
                "辉夜姬物语",
                "声之形",
                "千与千寻",
                "你的名字",
                "天气之子",
                "风之谷",
                "天空之城",
                "侧耳倾听",
                "哈尔的移动城堡",
                "GirlBandsCry",
                "MyGO!!!!!",
                "GirlBandsCry",
                "MyGO!!!!!",
                "GirlBandsCry",
                "MyGO!!!!!",
                "GirlBandsCry",
                "MyGO!!!!!",
                "AvaMujica",
                "少女终末旅行",
                "东方红魔乡",
                "东方永夜抄",
                "东方地灵殿",
                "东方文花帖",
                "东方星莲船",
                "东方心绮楼",
                 "东方三月精",
                 "东方妖妖梦",
                 "东方地灵殿",
                 "东方红魔乡",
                 "东方永夜抄",
                "习近平:伟大的独裁者"
        };
        for(int i = 0; i< bookNames.length; i++){
            Book book = new Book();
            book.setAvail(true);
            book.setPress(press[i%press.length]);
            book.setAuthor(authors[i % authors.length]);
            book.setBookName(bookNames[i]);
            bookMapper.addBook(book);
        }
    }



//    private testBorrowService() {
//
//    }
}

