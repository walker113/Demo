package stay.walker.com.test;

public class NameBean<T> {


    /**
     * personageId : 12
     * type : 0
     * tableId : 0
     * data :
     * content : 王子殿下，以后所有的工作就交给我吧！
     * direction : 0
     * createTime : 2019-04-02 09:56:41
     */

    private int personageId;
    private int type;
    private int tableId;
    private T data;
    private String content;
    private int direction;
    private String createTime;

    public int getPersonageId() {
        return personageId;
    }

    public void setPersonageId(int personageId) {
        this.personageId = personageId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
