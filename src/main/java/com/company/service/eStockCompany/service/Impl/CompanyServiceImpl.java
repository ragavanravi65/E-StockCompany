package com.company.service.eStockCompany.service.Impl;

import com.company.service.eStockCompany.Repository.CompanyRepository;
import com.company.service.eStockCompany.Repository.StockRepository;
import com.company.service.eStockCompany.mapper.SimpleSourceDestinationMapper;
import com.company.service.eStockCompany.model.DTO.Company;
import com.company.service.eStockCompany.model.DTO.CompanyWithStock;
import com.company.service.eStockCompany.model.entity.CompanyDetails;
import com.company.service.eStockCompany.model.entity.StockEntity;
import com.company.service.eStockCompany.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private StockRepository stockRepository;

    private SimpleSourceDestinationMapper mapperImpl
            = Mappers.getMapper(SimpleSourceDestinationMapper.class);

    @Override
    public Company registerCompanyService(Company company) {
        CompanyDetails postUpdateValue = companyRepository.save(mapperImpl.companyToDetails(company));
        return mapperImpl.DetailsToCompany(postUpdateValue);
    }

    @Override
    public CompanyWithStock fetchByCompanyCode(String companyCode) {
        Optional<CompanyDetails> findByCodeResult = companyRepository.findById(companyCode);
        CompanyDetails companyDetails = findByCodeResult.isPresent() ? findByCodeResult.get() : null;
        StockEntity stockData= stockRepository.findLatestByCompanyCode(companyCode);
        return CompanyWithStock.builder().company(mapperImpl.DetailsToCompany(companyDetails))
                .stock(mapperImpl.EntityToStock(stockData)).build();
    }

    @Override
    public List<CompanyWithStock> fetchAll() {
        List<CompanyWithStock> companyStockList=new ArrayList<>();
        List<CompanyDetails> findByCodeResult = companyRepository.findAll();
        findByCodeResult.stream().forEach(
                companyDet->
                {
                    StockEntity stockData=stockRepository.findLatestByCompanyCode(companyDet.getCompanyCode());
                    companyStockList.add(CompanyWithStock.builder()
                            .company(mapperImpl.DetailsToCompany(companyDet))
                            .stock(mapperImpl.EntityToStock(stockData))
                            .build());
                }
        );
        return companyStockList;
    }

    @Override
    @Transactional
    public String deleteById(String companyCode) {
        int deleteFlag=0;
        if(companyRepository.findById(companyCode).isPresent()) {
            companyRepository.deleteById(companyCode);
            deleteFlag+=2;
        }
        if(stockRepository.findByCompanyCode(companyCode) !=null) {
            stockRepository.deleteAllByCompanyCode(companyCode);
            deleteFlag+=3;
        }
        return deleteFlag==5?"successfully deleted the details of stock and company "+ companyCode:
                deleteFlag==2?"Removed from Company Database":
                        deleteFlag==3?"Removed from Stock Database":"No data available to delete";

    }
}
