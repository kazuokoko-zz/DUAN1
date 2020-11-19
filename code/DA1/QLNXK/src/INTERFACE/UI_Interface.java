/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INTERFACE;

/**
 *
 * @author ma-user
 * @param <E>
 */
public interface UI_Interface<E> {

    /**
     * xóa form trên UI
     */
    public void clearForm();

    /**
     * hiện data lên bảng
     *
     * @param beginIndex - vị trí đầu tiên trong list cần hiện
     * @param endIndex - vị trí cuối cùng trong list cần hiện
     */
    public void fillToTable(int beginIndex, int endIndex);

    /**
     * hiện data lên form
     *
     * @param e
     */
    public void showDetail(E e);

}
