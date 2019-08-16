package me.khmoon.shlibraryapi.diet.model.Respository;

import me.khmoon.shlibraryapi.diet.model.DietTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietTableRepository extends JpaRepository<DietTable, Long> {
}
