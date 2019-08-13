package com.csuft.wxl.pojo;

public class Permission {
	int id;
	String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + "]";
	}

	public enum PERMISSION_NAME {
		addProduct, deleteProduct, editProduct, updateProduct, listProduct, addOrder, deleteOrder, editOrder,
		updateOrder, listOrder
	}
}
