package exportData;

import org.springframework.data.jpa.repository.JpaRepository;


public interface DomainRepository extends JpaRepository<Domain, String> {

//    QiniuUpsertTask findByDomainName(String domainName);
//
//    List<QiniuUpsertTask> findByDomainNameIn(List<String> domainNames);
//
//    QiniuUpsertTask findByDomainNameAndDomainStatus(String domainName, String domainStatus);
//
//    List<QiniuUpsertTask> findByDomainStatus(String status);


    //    QiniuUpsertTask findByDomainNameAndActionStatus(String domainName, String actionStatus);
//    public List<QiniuUpsertTask> findByDomainNameIn(List<String> domainList);
//    public List<QiniuUpsertTask> findByDomainNameAndProtocol(String domainName, String protocol);

//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query(value = "update Coffee t set t.name=:newName where t.id=:id")
//    int updateDomainByIdWithActionSerialno(@Param(value = "id") Long id, @Param( value = "newName") String newName,
//                                       @Param(value = "domain_id") String domainId, @Param(value = "")
//
//                                       );


//    List<ChinacacheDomain> findByActionStatus(String status);
//
//    @Transactional
//    @Modifying
//    @Query(value = "update adapter.chinacache_domain t set ", nativeQuery = true)
//    int updateDomainIfUnsuccess(@Param(value = "domainName") String domainName);
}
