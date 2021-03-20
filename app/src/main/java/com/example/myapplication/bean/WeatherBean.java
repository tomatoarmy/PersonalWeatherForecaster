package com.example.myapplication.bean;

import java.util.List;

public class WeatherBean {


    /**
     * reason : 查询成功!
     * result : {"city":"嫩江","realtime":{"temperature":"-1","humidity":"59","info":"多云","wid":"01","direct":"东北风","power":"2级","aqi":"0"},"future":[{"date":"2021-03-20","temperature":"-8/3℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"东风转北风"},{"date":"2021-03-21","temperature":"-8/3℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"西北风"},{"date":"2021-03-22","temperature":"-4/7℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"西北风"},{"date":"2021-03-23","temperature":"-2/8℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"西北风"},{"date":"2021-03-24","temperature":"-3/6℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"东南风转西南风"}]}
     * error_code : 0
     */

    private String reason;
    private ResultDTO result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultDTO getResult() {
        return result;
    }

    public void setResult(ResultDTO result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultDTO {
        /**
         * city : 嫩江
         * realtime : {"temperature":"-1","humidity":"59","info":"多云","wid":"01","direct":"东北风","power":"2级","aqi":"0"}
         * future : [{"date":"2021-03-20","temperature":"-8/3℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"东风转北风"},{"date":"2021-03-21","temperature":"-8/3℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"西北风"},{"date":"2021-03-22","temperature":"-4/7℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"西北风"},{"date":"2021-03-23","temperature":"-2/8℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"西北风"},{"date":"2021-03-24","temperature":"-3/6℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"东南风转西南风"}]
         */

        private String city;
        private RealtimeDTO realtime;
        private List<FutureDTO> future;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public RealtimeDTO getRealtime() {
            return realtime;
        }

        public void setRealtime(RealtimeDTO realtime) {
            this.realtime = realtime;
        }

        public List<FutureDTO> getFuture() {
            return future;
        }

        public void setFuture(List<FutureDTO> future) {
            this.future = future;
        }

        public static class RealtimeDTO {
            /**
             * temperature : -1
             * humidity : 59
             * info : 多云
             * wid : 01
             * direct : 东北风
             * power : 2级
             * aqi : 0
             */

            private String temperature;
            private String humidity;
            private String info;
            private String wid;
            private String direct;
            private String power;
            private String aqi;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getHumidity() {
                return humidity;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getWid() {
                return wid;
            }

            public void setWid(String wid) {
                this.wid = wid;
            }

            public String getDirect() {
                return direct;
            }

            public void setDirect(String direct) {
                this.direct = direct;
            }

            public String getPower() {
                return power;
            }

            public void setPower(String power) {
                this.power = power;
            }

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }
        }

        public static class FutureDTO {
            /**
             * date : 2021-03-20
             * temperature : -8/3℃
             * weather : 多云
             * wid : {"day":"01","night":"01"}
             * direct : 东风转北风
             */

            private String date;
            private String temperature;
            private String weather;
            private WidDTO wid;
            private String direct;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WidDTO getWid() {
                return wid;
            }

            public void setWid(WidDTO wid) {
                this.wid = wid;
            }

            public String getDirect() {
                return direct;
            }

            public void setDirect(String direct) {
                this.direct = direct;
            }

            public static class WidDTO {
                /**
                 * day : 01
                 * night : 01
                 */

                private String day;
                private String night;

                public String getDay() {
                    return day;
                }

                public void setDay(String day) {
                    this.day = day;
                }

                public String getNight() {
                    return night;
                }

                public void setNight(String night) {
                    this.night = night;
                }
            }
        }
    }
}
