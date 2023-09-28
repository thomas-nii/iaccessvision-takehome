package com.example.iaccessvisiontakehome.repository;

import com.example.iaccessvisiontakehome.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query("SELECT DISTINCT c.ipaddress " +
            "FROM client c " +
            "WHERE c.name = :name OR c.application = :application OR c.environment = :environment")
    List<String> findDistinctIpsByNameOrApplicationOrEnvironment(@Param("name") String name,
                                                                 @Param("environment") String environment,
                                                                 @Param("application") String application);

    @Query("SELECT DISTINCT c.ipaddress " +
            "FROM client c " +
            "WHERE c.name = :name AND c.application = :application AND c.environment = :environment")
    List<String> findDistinctIpsByNameAndEnvironmentAndApplication(@Param("name") String name,
                                                                   @Param("environment") String environment,
                                                                   @Param("application") String application);

    @Query("SELECT DISTINCT c.ipaddress " +
            "FROM client c " +
            "WHERE c.name = :name AND c.environment = :environment")
    List<String> findDistinctIpsByNameAndEnvironment(@Param("name") String name,
                                                     @Param("environment") String environment);

    @Query("SELECT DISTINCT c.ipaddress " +
            "FROM client c " +
            "WHERE c.name = :name AND c.application = :application")
    List<String> findDistinctIpaddressByNameAndApplication(@Param("name") String name,
                                                           @Param("application") String application);

    @Query("SELECT DISTINCT c.ipaddress " +
            "FROM client c " +
            "WHERE c.environment = :environment AND c.application = :application")
    List<String> findDistinctIpaddressByEnvironmentAndApplication(@Param("environment") String environment,
                                                                  @Param("application") String application);

    @Modifying
    @Query("delete from client c where c.ipaddress = :ipaddress")
    void deleteByIpaddress(String ipaddress);
}
