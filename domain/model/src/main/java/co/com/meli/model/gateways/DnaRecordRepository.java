package co.com.meli.model.gateways;

import co.com.meli.model.DnaRecord;

public interface DnaRecordRepository {
    DnaRecord saveDnaRecord(DnaRecord dnaRecord);

    Long mutantCount();

    Long humanCount();
}
