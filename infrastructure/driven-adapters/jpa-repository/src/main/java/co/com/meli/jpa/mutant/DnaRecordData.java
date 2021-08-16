package co.com.meli.jpa.mutant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TBL_DNA_RECORD")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DnaRecordData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String[] dna;
    private Boolean mutant;
}
