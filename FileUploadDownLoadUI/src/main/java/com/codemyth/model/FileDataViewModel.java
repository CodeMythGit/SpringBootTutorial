package com.codemyth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FileDataViewModel {

    private List<FileData> itemList  = new ArrayList<>();

    public FileDataViewModel(List<FileData> fileDataList) {
        fileDataList.forEach(itemList::add);
    }
}
