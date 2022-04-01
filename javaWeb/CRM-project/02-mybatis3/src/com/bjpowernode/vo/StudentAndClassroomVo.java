package com.bjpowernode.vo;

public class StudentAndClassroomVo {
    //学生相关信息
    private String sid;
    private String sname;
    private Integer sage;
    private String saddress;

    //班级相关信息
    private String cid;
    private String cname;

    @Override
    public String toString() {
        return "StudentAndClassroomVo{" +
                "sid='" + sid + '\'' +
                ", sname='" + sname + '\'' +
                ", sage=" + sage +
                ", saddress='" + saddress + '\'' +
                ", cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                '}';
    }

    public StudentAndClassroomVo() {
    }

    public StudentAndClassroomVo(String sid, String sname, Integer sage, String saddress, String cid, String cname) {
        this.sid = sid;
        this.sname = sname;
        this.sage = sage;
        this.saddress = saddress;
        this.cid = cid;
        this.cname = cname;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getSage() {
        return sage;
    }

    public void setSage(Integer sage) {
        this.sage = sage;
    }

    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
