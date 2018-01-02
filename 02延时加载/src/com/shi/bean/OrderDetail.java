package com.shi.bean;

public class OrderDetail {
      private int id;
      private int orders_id;
      private int item_id;
      private int item_num;

      public int getId() {
            return id;
      }

      public void setId(int id) {
            this.id = id;
      }

      public int getOrders_id() {
            return orders_id;
      }

      public void setOrders_id(int orders_id) {
            this.orders_id = orders_id;
      }

      public int getItem_id() {
            return item_id;
      }

      public void setItem_id(int item_id) {
            this.item_id = item_id;
      }

      public int getItem_num() {
            return item_num;
      }

      public void setItem_num(int item_num) {
            this.item_num = item_num;
      }

      @Override
      public String toString() {
            return "OrderDetail{" +
                    "id=" + id +
                    ", orders_id=" + orders_id +
                    ", item_id=" + item_id +
                    ", item_num=" + item_num +
                    '}';
      }
}
