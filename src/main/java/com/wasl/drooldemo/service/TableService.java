package com.wasl.drooldemo.service;

import com.wasl.drooldemo.entity.Table;
import com.wasl.drooldemo.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    public List<Table> findAll() {
        return tableRepository.findAll();
    }

    public Optional<Table> findById(String id) {
        return tableRepository.findById(id);
    }

    public Table save(Table table) {
        return tableRepository.save(table);
    }

    public void deleteById(String id) {
        tableRepository.deleteById(id);
    }

    public Optional<Table> findByName(String name) {
        return tableRepository.findByName(name);
    }

    public boolean validateAndFlattenArray(Object array, List<Integer> size, List<Integer> flattenedArray) {
        return validateAndFlattenArrayRecursive(array, size, 0, flattenedArray);
    }

    private boolean validateAndFlattenArrayRecursive(Object array, List<Integer> size, int dimension, List<Integer> flattenedArray) {
        if (dimension >= size.size()) {
            return false;
        }

        if (!(array instanceof List<?>)) {
            return false;
        }

        List<?> arrayList = (List<?>) array;
        if (arrayList.size() != size.get(dimension)) {
            return false;
        }

        if (dimension == size.size() - 1) {
            for (Object element : arrayList) {
                if (!(element instanceof Integer)) {
                    return false;
                }
                flattenedArray.add((Integer) element);
            }
            return true;
        } else {
            for (Object element : arrayList) {
                if (!validateAndFlattenArrayRecursive(element, size, dimension + 1, flattenedArray)) {
                    return false;
                }
            }
            return true;
        }
    }
}
