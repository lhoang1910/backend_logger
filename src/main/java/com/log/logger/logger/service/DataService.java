package com.log.logger.logger.service;

import com.log.logger.logger.entity.DataEntity;
import com.log.logger.logger.repository.DataRepository;
import com.log.logger.logger.request.CreateDataRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DataService {
    private final DataRepository dataRepository;
    protected final transient Logger logger = LogManager.getLogger(this.getClass());
    protected Logger getLogger() {
        return this.logger;
    }

    public void create(CreateDataRequest request) throws IOException {
       DataEntity savedData = dataRepository.save(dataMapper(request));
       if (ObjectUtils.isEmpty(savedData)){
           logger.error(">>>>>>>>>>>>>>>>>>>>>>>>Lưu data thất bại>>>>>>>>>>>>");
            return;
       }
       logger.info(">>>>>>>>>>Lưu data thành công>>>>>>>>>");
    }

    public void delete(String id){
        try {
            dataRepository.deleteById(id);
            logger.info(">>>>>>>>>>Xóa data với ID: {} thành công>>>>>>>>>>", id);
        } catch (Exception e) {
            logger.error(">>>>>>>>>>Xóa data với ID: {} thất bại>>>>>>>>>>", id, e);
        }
    }

    public DataEntity get(String id) {
        try {
            DataEntity data = dataRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy data với ID: " + id));
            logger.info(">>>>>>>>>>Lấy data với ID: {} thành công>>>>>>>>>>", id);
            return data;
        } catch (NoSuchElementException e) {
            logger.error(">>>>>>>>>>Lấy data với ID: {} thất bại: Không tìm thấy>>>>>>>>>>", id, e);
            throw e;
        } catch (Exception e) {
            logger.error(">>>>>>>>>>Lấy data với ID: {} thất bại>>>>>>>>>>", id, e);
            throw e;
        }
    }

    public List<DataEntity> getAll() {
        return dataRepository.findAll();
    }

    public static DataEntity dataMapper(CreateDataRequest request){
        return DataEntity.builder()
                .id(UUID.randomUUID().toString())
                .fileSize(request.getFileSize())
                .fileType(request.getFileType())
                .fileName(request.getFileName())
                .timeSent(request.getTimeSent())
                .timeReceived(request.getTimeReceived())
                .content(request.getContent())
                .build();
    }
}
