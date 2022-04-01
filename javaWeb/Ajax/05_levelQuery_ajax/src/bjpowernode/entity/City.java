package bjpowernode.entity;

public class City {
    private String name;
    private Integer pid;

    public City() {
    }

    public City(String name, Integer pid) {
        this.name = name;
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
