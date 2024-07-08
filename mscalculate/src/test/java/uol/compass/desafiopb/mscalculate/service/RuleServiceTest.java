package uol.compass.desafiopb.mscalculate.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uol.compass.desafiopb.mscalculate.exception.InvalidRequestException;
import uol.compass.desafiopb.mscalculate.exception.ResourceNotFoundException;
import uol.compass.desafiopb.mscalculate.model.Rule;
import uol.compass.desafiopb.mscalculate.repository.RuleRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static uol.compass.desafiopb.mscalculate.constant.RuleConstant.INVALID_RULE;
import static uol.compass.desafiopb.mscalculate.constant.RuleConstant.RULE;

@ExtendWith(MockitoExtension.class)
public class RuleServiceTest {

    @InjectMocks
    private RuleService ruleService;

    @Mock
    private RuleRepository ruleRepository;

    @Test
    public void saveRuletWithValidData(){
        when(ruleRepository.save(RULE)).thenReturn(RULE);

        Rule sut = ruleService.save(RULE);

        assertThat(sut).isEqualTo(RULE);
    }

    @Test
    public void saveRuleWithInvalidData(){
        when(ruleRepository.save(INVALID_RULE)).thenThrow(InvalidRequestException.class);

        assertThatThrownBy(() -> ruleService.save(INVALID_RULE)).isInstanceOf(InvalidRequestException.class);

    }

    @Test
    public void getRuleWithExistingId(){
        when(ruleRepository.findById("1")).thenReturn(Optional.of(RULE));

        Optional<Rule> sut = ruleService.getByID("1");

        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(RULE);
    }

    @Test
    public void getRuletWithUnexistingId(){
        when(ruleRepository.findById("1")).thenReturn(Optional.empty());

        Optional<Rule> sut = ruleService.getByID("1");

        assertThat(sut).isEmpty();
    }

    @Test
    public void deleteRuleWithExistingId(){
        assertThatCode(() -> ruleService.deleteById("1")).doesNotThrowAnyException();
    }

    @Test
    public void deleteRuleWithUnexistingId() {
        doThrow(new ResourceNotFoundException("")).when(ruleRepository).deleteById("99");

        assertThatThrownBy(() -> ruleService.deleteById("99")).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void updateRuleWithValidData() {

        when(ruleRepository.save(RULE)).thenReturn(RULE);

        Rule updatedRule = ruleService.update(RULE);

        assertThat(updatedRule).isEqualTo(RULE);
    }

    @Test
    public void updateRuleWithInvalidData() {
        when(ruleRepository.save(INVALID_RULE)).thenThrow(InvalidRequestException.class);

        assertThatThrownBy(() -> ruleService.update(INVALID_RULE)).isInstanceOf(InvalidRequestException.class);
    }

}