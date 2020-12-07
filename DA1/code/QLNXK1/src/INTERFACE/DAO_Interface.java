/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INTERFACE;

import java.util.ArrayList;

/**
 *
 * @author ma-user
 * @param <E>
 */
public interface DAO_Interface<E> {

    /**
     * thêm dữ liệu vào database
     *
     * @param e - đối tượng cần thêm
     */
    public  boolean insert(E e);

    /**
     * cập nhật dữ liệu vào database
     *
     * @param e - đối tượng cần cập nhật
     */
    public boolean update(E e);

    /**
     * xóa dữ liệu trong database
     *
     * @param e - đối tượng cần xóa
     */
    public boolean delete(E e);

    /**
     * thêm dữ liệu vào database
     *
     * @param param - đối tượng mảng với mỗi phần tử là object chứa 2 giá trị là
     * tên cột trong bảng trong csdl và giá trị cần tìm
     * @return
     */
    public E selectByColumn(Object... param);

    /**
     * thêm dữ liệu vào database
     *
     * @param param - đối tượng mảng với mỗi phần tử là object chứa 2 giá trị là
     * tên cột trong bảng trong csdl và giá trị cần tìm
     * @return
     */
    public ArrayList<E> selectAllByColumn(Object... param);

}
