/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package models.dao;

import java.util.List;
import models.dto.Mobile;

/**
 *
 * @author admin
 */
public interface I_MobileDAO {
    List<Mobile> findAll();
}
