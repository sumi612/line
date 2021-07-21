package me.zhengjie.orcl.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.orcl.repository.LineloadRepository;
import me.zhengjie.orcl.service.LineloadService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LineloadServiceImpl implements LineloadService {
    private final LineloadRepository lineloadRepository;

    @Override
    public List<Map<String, String>> queryall(String ycid) {
        return lineloadRepository.queryall(ycid);
    }
}
