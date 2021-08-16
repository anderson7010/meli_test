package co.com.meli.jpa.mutant;

import co.com.meli.model.DnaRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DnaRecordMapper {
    DnaRecord toEntity(DnaRecordData dnaRecordData);

    DnaRecordData toData(DnaRecord dnaRecord);
}
