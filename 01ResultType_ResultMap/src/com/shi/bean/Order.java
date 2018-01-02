package com.shi.bean;

public class Order {
      int id;
      int user_id;
      String number;
      String note;

      public int getId() {
            return id;
      }

      public void setId(int id) {
            this.id = id;
      }

      public int getUser_id() {
            return user_id;
      }

      public void setUser_id(int user_id) {
            this.user_id = user_id;
      }

      public String getNumber() {
            return number;
      }

      public void setNumber(String number) {
            this.number = number;
      }

      public String getNote() {
            return note;
      }

      public void setNote(String note) {
            this.note = note;
      }

      @Override
      public String toString() {
            return "Order{" +
                    "id=" + id +
                    ", user_id=" + user_id +
                    ", number='" + number + '\'' +
                    ", note='" + note + '\'' +
                    '}';
      }
}
