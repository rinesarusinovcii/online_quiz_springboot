package com.rinesarusinovci.online_quizzes_vue_back.services.impls;



import com.rinesarusinovci.online_quizzes_vue_back.dto.ChoiceDto;
import com.rinesarusinovci.online_quizzes_vue_back.entities.Question;
import com.rinesarusinovci.online_quizzes_vue_back.mapper.ChoiceMapper;
import com.rinesarusinovci.online_quizzes_vue_back.repositories.ChoiceRepository;
import com.rinesarusinovci.online_quizzes_vue_back.repositories.QuestionRepository;
import com.rinesarusinovci.online_quizzes_vue_back.services.ChoiceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChoiceServiceImpl implements ChoiceService {

    private final ChoiceMapper choiceMapper;
    private final ChoiceRepository choiceRepository;
    private final QuestionRepository questionRepository;

    @Override
    public List<ChoiceDto> findAll() {
        return choiceMapper.toDtos(choiceRepository.findAll());
    }

    @Override
    public ChoiceDto findById(Long id) {
        return choiceRepository.findById(id)
                .map(choiceMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Choice with id " + id + " not found"));
    }

    @Override
    public ChoiceDto add(ChoiceDto model) {
        return save(model);
    }

    @Override
    public ChoiceDto modify(Long id, ChoiceDto model) {
        if (model.getId() == null || !id.equals(model.getId())) {
            throw new IllegalArgumentException("Id does not match");
        }


        if (!choiceRepository.existsById(id)) {
            throw new EntityNotFoundException("Choice with id " + id + " not found");
        }

        return save(model);
    }

    @Override
    public void removeById(Long id) {
        if (!choiceRepository.existsById(id)) {
            throw new EntityNotFoundException("Choice with id " + id + " not found");
        }

        choiceRepository.deleteById(id);
    }

    private ChoiceDto save(ChoiceDto model) {
        var entity = choiceMapper.toEntity(model);

        // Merr pyetjen nëse modeli ka questionId (duhet shtuar në DTO nëse nuk është)
        if (model.getQuestionId() != null) {
            Question question = questionRepository.findById(model.getQuestionId())
                    .orElseThrow(() -> new EntityNotFoundException("Question with id " + model.getQuestionId() + " not found"));
            entity.setQuestion(question);
        }

        var saved = choiceRepository.save(entity);
        return choiceMapper.toDto(saved);
    }
}

