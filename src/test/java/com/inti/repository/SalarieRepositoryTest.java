package com.inti.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inti.model.Salarie;

@ExtendWith(SpringExtension.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SalarieRepositoryTest {
	
	Salarie s1, savedSalarie;
	
	@Autowired
	ISalarieRepository salarieRepository;
	
	@BeforeEach
	public void setUp()
	{
		s1 = new Salarie("Dupont", "Jean", "test@test.fr");
		savedSalarie = salarieRepository.save(s1);
	}
	
	@Test
	public void saveSalarie()
	{		
		assertThat(savedSalarie).isNotNull();
		assertThat(savedSalarie.getId()).isGreaterThan(0);
	}
	
	@Test
	public void getSalarie()
	{
		// GIVEN
		
		// WHEN
		Salarie s2 = salarieRepository.getReferenceById(savedSalarie.getId());
		
		// THEN
		assertThat(s2).isNotNull();
		assertThat(s2.getId()).isEqualTo(savedSalarie.getId());
		assertThat(s2.getNom()).isEqualTo(savedSalarie.getNom());
		assertThat(s2.getPrenom()).isEqualTo("Jean");
		assertThat(s2.getEmail()).isEqualTo(savedSalarie.getEmail());
		assertThat(s2).isEqualTo(savedSalarie);
		
	}
	
	@Test
	public void deleteSalarie()
	{
		// WHEN
		salarieRepository.delete(savedSalarie);
		
		// THEN
		Assertions.assertThrows(Exception.class, () -> salarieRepository.getReferenceById(savedSalarie.getId()));
	}
	
	@Test
	public void updateSalarie()
	{
		// WHEN
		savedSalarie.setNom("Durand");
		savedSalarie.setPrenom("Louis");
		savedSalarie.setEmail("info@info.fr");
		
		Salarie salarieModified = salarieRepository.save(savedSalarie);
		
		// THEN
		assertThat(salarieModified).isNotNull();
		assertThat(salarieModified.getNom()).isEqualTo("Durand");
		assertThat(salarieModified.getPrenom()).isEqualTo("Louis");
		assertThat(salarieModified.getEmail()).isEqualTo("info@info.fr");
	}
	
	@Test
	public void getAllSalarie()
	{
		// Given
		Salarie s3 = salarieRepository.save(new Salarie("Marie", "Sophie", "test@te.fr"));
		Salarie s4 = salarieRepository.save(new Salarie("Durand", "Paul", "tes@te.fr"));
		
		// When
		List<Salarie> listeS = salarieRepository.findAll();
		
		// Then
		assertThat(listeS).isNotEmpty();
		assertThat(listeS).hasSize(3);
		assertThat(listeS.get(0).getClass()).hasSameClassAs(Salarie.class);
		assertThat(listeS.get(1).toString()).hasToString(s3.toString());
	}
	
	@Test
	public void getSalarieByNom()
	{
		
		Salarie s3 = salarieRepository.save(new Salarie("Dupont", "Sophie", "test@te.fr"));
		
		// When
		List<Salarie> listeS = salarieRepository.findByNom("Dupont");
		
		// Then
		assertThat(listeS).isNotEmpty();
		assertThat(listeS.get(0).getNom()).isEqualTo("Dupont");
	}
}
