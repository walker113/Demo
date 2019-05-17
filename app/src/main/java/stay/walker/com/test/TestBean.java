package stay.walker.com.test;

import java.util.List;

public class TestBean {


    /**
     * data : [{"personageId":1737,"type":1,"tableId":191,"data":{"flowId":191,"userVO":{"userId":1,"userName":"Ludashi","userAvatar":"https://park-dev.oss-cn-shenzhen.aliyuncs.com/dev/20190412160d3e9eac-15b8-4861-8d12-e3dd9944ef03.jpg","handleId":""},"auditName":"加班","auditCode":"201904241751019158899117079","departmentId":77,"departmentName":"研发部","formData":"[]","status":0,"handleVOList":[{"multitudeType":2,"isAgree":0,"opinion":"","atPresent":true,"person":1,"userVOList":[{"userId":1,"userName":"Ludashi","userAvatar":"https://park-dev.oss-cn-shenzhen.aliyuncs.com/dev/20190412160d3e9eac-15b8-4861-8d12-e3dd9944ef03.jpg","handleId":316}]},{"multitudeType":3,"isAgree":0,"opinion":"","atPresent":false,"person":1,"userVOList":[{"userId":1,"userName":"Ludashi","userAvatar":"https://park-dev.oss-cn-shenzhen.aliyuncs.com/dev/20190412160d3e9eac-15b8-4861-8d12-e3dd9944ef03.jpg","handleId":""}]}],"createTime":"2019-04-24 17:51:01","completeTime":"","type":0},"content":"老铁，审批已帮你送达了！","direction":0,"createTime":"2019-04-24 17:51:02"},{"personageId":1738,"type":1,"tableId":191,"data":{"flowId":191,"userVO":{"userId":1,"userName":"Ludashi","userAvatar":"https://park-dev.oss-cn-shenzhen.aliyuncs.com/dev/20190412160d3e9eac-15b8-4861-8d12-e3dd9944ef03.jpg","handleId":""},"auditName":"加班","auditCode":"201904241751019158899117079","departmentId":77,"departmentName":"研发部","formData":"[]","status":0,"handleVOList":[{"multitudeType":2,"isAgree":0,"opinion":"","atPresent":true,"person":1,"userVOList":[{"userId":1,"userName":"Ludashi","userAvatar":"https://park-dev.oss-cn-shenzhen.aliyuncs.com/dev/20190412160d3e9eac-15b8-4861-8d12-e3dd9944ef03.jpg","handleId":316}]},{"multitudeType":3,"isAgree":0,"opinion":"","atPresent":false,"person":1,"userVOList":[{"userId":1,"userName":"Ludashi","userAvatar":"https://park-dev.oss-cn-shenzhen.aliyuncs.com/dev/20190412160d3e9eac-15b8-4861-8d12-e3dd9944ef03.jpg","handleId":""}]}],"createTime":"2019-04-24 17:51:01","completeTime":"","type":0},"content":"老铁，有新审批需要处理，盘它？","direction":0,"createTime":"2019-04-24 17:51:02"}]
     * code : 0
     * httpStatus : 200
     * path :
     * timestamp : 1556114957885
     * exception :
     * success : true
     */

    private int code;
    private int httpStatus;
    private String path;
    private String timestamp;
    private String exception;
    private boolean success;
    private List<DataBeanX> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBeanX> getData() {
        return data;
    }

    public void setData(List<DataBeanX> data) {
        this.data = data;
    }

    public static class DataBeanX<T> {
        /**
         * personageId : 1737
         * type : 1
         * tableId : 191
         * data : {"flowId":191,"userVO":{"userId":1,"userName":"Ludashi","userAvatar":"https://park-dev.oss-cn-shenzhen.aliyuncs.com/dev/20190412160d3e9eac-15b8-4861-8d12-e3dd9944ef03.jpg","handleId":""},"auditName":"加班","auditCode":"201904241751019158899117079","departmentId":77,"departmentName":"研发部","formData":"[]","status":0,"handleVOList":[{"multitudeType":2,"isAgree":0,"opinion":"","atPresent":true,"person":1,"userVOList":[{"userId":1,"userName":"Ludashi","userAvatar":"https://park-dev.oss-cn-shenzhen.aliyuncs.com/dev/20190412160d3e9eac-15b8-4861-8d12-e3dd9944ef03.jpg","handleId":316}]},{"multitudeType":3,"isAgree":0,"opinion":"","atPresent":false,"person":1,"userVOList":[{"userId":1,"userName":"Ludashi","userAvatar":"https://park-dev.oss-cn-shenzhen.aliyuncs.com/dev/20190412160d3e9eac-15b8-4861-8d12-e3dd9944ef03.jpg","handleId":""}]}],"createTime":"2019-04-24 17:51:01","completeTime":"","type":0}
         * content : 老铁，审批已帮你送达了！
         * direction : 0
         * createTime : 2019-04-24 17:51:02
         */

        private int personageId;
        private int type;
        private int tableId;
        private List<T> data;
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

        public List<T> getData() {
            return data;
        }

        public void setData(List<T> data) {
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

        public static class DataBean {
            /**
             * flowId : 191
             * userVO : {"userId":1,"userName":"Ludashi","userAvatar":"https://park-dev.oss-cn-shenzhen.aliyuncs.com/dev/20190412160d3e9eac-15b8-4861-8d12-e3dd9944ef03.jpg","handleId":""}
             * auditName : 加班
             * auditCode : 201904241751019158899117079
             * departmentId : 77
             * departmentName : 研发部
             * formData : []
             * status : 0
             * handleVOList : [{"multitudeType":2,"isAgree":0,"opinion":"","atPresent":true,"person":1,"userVOList":[{"userId":1,"userName":"Ludashi","userAvatar":"https://park-dev.oss-cn-shenzhen.aliyuncs.com/dev/20190412160d3e9eac-15b8-4861-8d12-e3dd9944ef03.jpg","handleId":316}]},{"multitudeType":3,"isAgree":0,"opinion":"","atPresent":false,"person":1,"userVOList":[{"userId":1,"userName":"Ludashi","userAvatar":"https://park-dev.oss-cn-shenzhen.aliyuncs.com/dev/20190412160d3e9eac-15b8-4861-8d12-e3dd9944ef03.jpg","handleId":""}]}]
             * createTime : 2019-04-24 17:51:01
             * completeTime :
             * type : 0
             */

            private float flowId;
            private UserVOBean userVO;
            private String auditName;
            private String auditCode;
            private int departmentId;
            private String departmentName;
            private String formData;
            private int status;
            private String createTime;
            private String completeTime;
            private int type;
            private List<HandleVOListBean> handleVOList;

            public float getFlowId() {
                return flowId;
            }

            public void setFlowId(float flowId) {
                this.flowId = flowId;
            }

            public UserVOBean getUserVO() {
                return userVO;
            }

            public void setUserVO(UserVOBean userVO) {
                this.userVO = userVO;
            }

            public String getAuditName() {
                return auditName;
            }

            public void setAuditName(String auditName) {
                this.auditName = auditName;
            }

            public String getAuditCode() {
                return auditCode;
            }

            public void setAuditCode(String auditCode) {
                this.auditCode = auditCode;
            }

            public int getDepartmentId() {
                return departmentId;
            }

            public void setDepartmentId(int departmentId) {
                this.departmentId = departmentId;
            }

            public String getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(String departmentName) {
                this.departmentName = departmentName;
            }

            public String getFormData() {
                return formData;
            }

            public void setFormData(String formData) {
                this.formData = formData;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getCompleteTime() {
                return completeTime;
            }

            public void setCompleteTime(String completeTime) {
                this.completeTime = completeTime;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public List<HandleVOListBean> getHandleVOList() {
                return handleVOList;
            }

            public void setHandleVOList(List<HandleVOListBean> handleVOList) {
                this.handleVOList = handleVOList;
            }

            public static class UserVOBean {
                /**
                 * userId : 1
                 * userName : Ludashi
                 * userAvatar : https://park-dev.oss-cn-shenzhen.aliyuncs.com/dev/20190412160d3e9eac-15b8-4861-8d12-e3dd9944ef03.jpg
                 * handleId :
                 */

                private int userId;
                private String userName;
                private String userAvatar;
                private String handleId;

                public int getUserId() {
                    return userId;
                }

                public void setUserId(int userId) {
                    this.userId = userId;
                }

                public String getUserName() {
                    return userName;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public String getUserAvatar() {
                    return userAvatar;
                }

                public void setUserAvatar(String userAvatar) {
                    this.userAvatar = userAvatar;
                }

                public String getHandleId() {
                    return handleId;
                }

                public void setHandleId(String handleId) {
                    this.handleId = handleId;
                }
            }

            public static class HandleVOListBean {
                /**
                 * multitudeType : 2
                 * isAgree : 0
                 * opinion :
                 * atPresent : true
                 * person : 1
                 * userVOList : [{"userId":1,"userName":"Ludashi","userAvatar":"https://park-dev.oss-cn-shenzhen.aliyuncs.com/dev/20190412160d3e9eac-15b8-4861-8d12-e3dd9944ef03.jpg","handleId":316}]
                 */

                private int multitudeType;
                private int isAgree;
                private String opinion;
                private boolean atPresent;
                private int person;
                private List<UserVOListBean> userVOList;

                public int getMultitudeType() {
                    return multitudeType;
                }

                public void setMultitudeType(int multitudeType) {
                    this.multitudeType = multitudeType;
                }

                public int getIsAgree() {
                    return isAgree;
                }

                public void setIsAgree(int isAgree) {
                    this.isAgree = isAgree;
                }

                public String getOpinion() {
                    return opinion;
                }

                public void setOpinion(String opinion) {
                    this.opinion = opinion;
                }

                public boolean isAtPresent() {
                    return atPresent;
                }

                public void setAtPresent(boolean atPresent) {
                    this.atPresent = atPresent;
                }

                public int getPerson() {
                    return person;
                }

                public void setPerson(int person) {
                    this.person = person;
                }

                public List<UserVOListBean> getUserVOList() {
                    return userVOList;
                }

                public void setUserVOList(List<UserVOListBean> userVOList) {
                    this.userVOList = userVOList;
                }

                public static class UserVOListBean {
                    /**
                     * userId : 1
                     * userName : Ludashi
                     * userAvatar : https://park-dev.oss-cn-shenzhen.aliyuncs.com/dev/20190412160d3e9eac-15b8-4861-8d12-e3dd9944ef03.jpg
                     * handleId : 316
                     */

                    private int userId;
                    private String userName;
                    private String userAvatar;
                    private int handleId;

                    public int getUserId() {
                        return userId;
                    }

                    public void setUserId(int userId) {
                        this.userId = userId;
                    }

                    public String getUserName() {
                        return userName;
                    }

                    public void setUserName(String userName) {
                        this.userName = userName;
                    }

                    public String getUserAvatar() {
                        return userAvatar;
                    }

                    public void setUserAvatar(String userAvatar) {
                        this.userAvatar = userAvatar;
                    }

                    public int getHandleId() {
                        return handleId;
                    }

                    public void setHandleId(int handleId) {
                        this.handleId = handleId;
                    }
                }
            }
        }
    }
}
