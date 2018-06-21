package cn.xxyangyoulin.shiyue.data.bean;

import java.io.Serializable;
import java.util.List;

public class DailyWrapper extends BaseBean {

    /**
     * msg :
     * code : 1
     * data : [{"dailytime":"2018-06-18 17:32:04","title":"贈談命嚴叔寓","author":"李長沙","tid":1,"content":"平時已秉班揚筆，暇處不妨甘石經。|吾里忻傳日邊信，君言頻中斗杓星。|會稽夫子餘詩禮，巴蜀君平舊典型。|歷歷周天三百度，更參璿玉到虞廷。","author_id":12537,"dailyid":5},{"dailytime":"2018-06-18 17:32:04","title":"送戴太博  其一","author":"杜司直","tid":2,"content":"昨者題輿政未成，鄰封騎竹已歡迎。|翱翔忽看天邊舉，來去從教境上爭。|玉笥江山應改色，青原風月未忘情。|兩邦父老休啼哭，總在甘棠蔭裏行。","author_id":12538,"dailyid":6},{"dailytime":"2018-06-18 17:32:04","title":"送戴太博  其二","author":"杜司直","tid":3,"content":"瓊佩霞裾雲錦裳，胸中指塞貯琳瑯。|詞章莫測波瀾闊，談笑惟圖書傳香。|與我多應鄰臭味，於人寧有別肝腸。|一州斗大非公處，早晚催歸白玉堂。","author_id":12538,"dailyid":7},{"dailytime":"2018-06-18 15:41:23","title":"六州","author":"和岘","tid":2,"content":"严夜警，铜莲漏迟迟。\n清禁肃，森陛戟，羽卫俨皇闱。\n角声励，钲鼓攸宜。\n金管成雅奏，逐吹逶迤。\n荐苍璧，郊祀神祗。\n属景运纯禧。\n京坻丰衍，群材乐育，诸侯述职，盛德服蛮夷。\n殊祥萃，九苞丹凤来仪。\n膏露降，和气洽，三秀焕灵芝。\n鸿猷播，史册相辉。\n张四维。\n卜世永固丕基。\n敷玄化，荡荡无为。","author_id":305,"dailyid":4},{"dailytime":"2018-06-17 15:41:03","title":"导引","author":"和岘","tid":1,"content":"气和玉烛，睿化著鸿明。\n缇管一阳生。\n郊盛礼燔柴毕，旋轸凤凰城。\n森罗仪卫振华缨。\n载路溢欢声。\n皇图大业超前古，垂象泰阶平。\n岁时丰衍，九土乐升平。\n睹寰海澄清。\n道高尧舜垂衣治，日月并文明。","author_id":305,"dailyid":1},{"dailytime":"2018-06-16 15:41:13","title":"点绛唇","author":"王禹","tid":4,"content":"雨恨云愁，江南依旧称佳丽。\n水村渔市。\n一缕孤烟细。\n天际征鸿，遥认行如缀。\n平生事。\n此时凝睇。\n谁会凭阑意。","author_id":954,"dailyid":2},{"dailytime":"2018-06-15 15:41:23","title":"十二时・忆少年","author":"和岘","tid":3,"content":"承宝运，驯致隆平。\n鸿庆被寰瀛。\n时清俗阜，治定功成。\n遐迩咏由庚。\n严郊祀，文物声明。\n会天正、星拱奏严更。\n布羽仪簪缨。\n宸心虔洁，明德播惟馨。\n动苍冥。\n神降享精诚。\n燔柴半，万乘移天仗，肃銮辂旋衡。\n千官云拥，群后葵倾。\n玉帛旅明庭。\n韶荐，金奏谐声。\n集休亨。\n皇泽浃黎庶，普率洽恩荣。\n仰钦元后，睿圣贯三灵。","author_id":305,"dailyid":3}]
     */

    private List<Daily> data;

    public List<Daily> getData() {
        return data;
    }

    public void setData(List<Daily> data) {
        this.data = data;
    }

    public static class Daily implements Serializable {
        /**
         * dailytime : 2018-06-18 17:32:04
         * title : 贈談命嚴叔寓
         * author : 李長沙
         * tid : 1
         * content : 平時已秉班揚筆，暇處不妨甘石經。|吾里忻傳日邊信，君言頻中斗杓星。|會稽夫子餘詩禮，巴蜀君平舊典型。|歷歷周天三百度，更參璿玉到虞廷。
         * author_id : 12537
         * dailyid : 5
         */

        private String dailytime;
        private String title;
        private String author;
        private int tid;
        private int type;
        private int ccount;
        private String content;
        private int author_id;
        private int dailyid;

        public String getDailytime() {
            return dailytime;
        }

        public void setDailytime(String dailytime) {
            this.dailytime = dailytime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getTid() {
            return tid;
        }

        public void setTid(int tid) {
            this.tid = tid;
        }

        public int getType() {
            return type;
        }

        public Daily setType(int type) {
            this.type = type;
            return this;
        }

        public int getCcount() {
            return ccount;
        }

        public Daily setCcount(int ccount) {
            this.ccount = ccount;
            return this;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getAuthor_id() {
            return author_id;
        }

        public void setAuthor_id(int author_id) {
            this.author_id = author_id;
        }

        public int getDailyid() {
            return dailyid;
        }

        public void setDailyid(int dailyid) {
            this.dailyid = dailyid;
        }
    }
}
