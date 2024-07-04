/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package main.repo;

import java.util.List;
import main.dto.OwnerDTO;

/**
 *
 * @author hp
 */
public interface OwnerRepoCustom {
    List<OwnerDTO> findByCriteria(String firstName, String lastName);
}
