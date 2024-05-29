package com.wzr.food.logic.model;

import java.io.Serializable;
import java.util.List;

public class LivesBean implements Serializable{

    private int code;
    private String message;
    private Obj obj;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setObj(Obj obj) {
        this.obj = obj;
    }

    public Obj getObj() {
        return obj;
    }

    public class Obj implements Serializable{

        private Ret ret;
        private int code;
        private String requestId;

        public void setRet(Ret ret) {
            this.ret = ret;
        }

        public Ret getRet() {
            return ret;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public String getRequestId() {
            return requestId;
        }

        public class Ret implements Serializable{

            private int pnum;
            private int totalRecords;
            private int records;
            private List<List1> list;
            private int totalPnum;
            public void setPnum(int pnum) {
                this.pnum = pnum;
            }
            public int getPnum() {
                return pnum;
            }

            public void setTotalRecords(int totalRecords) {
                this.totalRecords = totalRecords;
            }
            public int getTotalRecords() {
                return totalRecords;
            }

            public void setRecords(int records) {
                this.records = records;
            }
            public int getRecords() {
                return records;
            }

            public void setList(List<List1> list) {
                this.list = list;
            }
            public List<List1> getList() {
                return list;
            }

            public void setTotalPnum(int totalPnum) {
                this.totalPnum = totalPnum;
            }
            public int getTotalPnum() {
                return totalPnum;
            }

            public class List1 implements Serializable {

                private int duration;
                private long uid;
                private String filename;
                private int recordStatus;
                private int needRecord;
                private String name;
                private int format;
                private long ctime;
                private int type;
                private int status;
                private String cid;
                public void setDuration(int duration) {
                    this.duration = duration;
                }
                public int getDuration() {
                    return duration;
                }

                public void setUid(long uid) {
                    this.uid = uid;
                }
                public long getUid() {
                    return uid;
                }

                public void setFilename(String filename) {
                    this.filename = filename;
                }
                public String getFilename() {
                    return filename;
                }

                public void setRecordStatus(int recordStatus) {
                    this.recordStatus = recordStatus;
                }
                public int getRecordStatus() {
                    return recordStatus;
                }

                public void setNeedRecord(int needRecord) {
                    this.needRecord = needRecord;
                }
                public int getNeedRecord() {
                    return needRecord;
                }

                public void setName(String name) {
                    this.name = name;
                }
                public String getName() {
                    return name;
                }

                public void setFormat(int format) {
                    this.format = format;
                }
                public int getFormat() {
                    return format;
                }

                public void setCtime(long ctime) {
                    this.ctime = ctime;
                }
                public long getCtime() {
                    return ctime;
                }

                public void setType(int type) {
                    this.type = type;
                }
                public int getType() {
                    return type;
                }

                public void setStatus(int status) {
                    this.status = status;
                }
                public int getStatus() {
                    return status;
                }

                public void setCid(String cid) {
                    this.cid = cid;
                }
                public String getCid() {
                    return cid;
                }

            }

        }

    }
}
