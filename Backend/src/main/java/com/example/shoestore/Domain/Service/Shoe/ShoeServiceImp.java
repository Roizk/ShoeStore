package com.example.shoestore.Domain.Service.Shoe;


import com.example.shoestore.Domain.DTO.ShoeDTO;
import com.example.shoestore.Domain.DTO.ShoeDTOMapper;
import com.example.shoestore.Domain.Model.Shoe.Shoe;
import com.example.shoestore.Persistence.Repository.ShoeRepo;
import com.example.shoestore.Persistence.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoeServiceImp implements ShoeService {
    @Autowired
    private ShoeRepo shoeRepo;
    @Autowired
    private ShoeDTOMapper shoeDTOMapper;
    @Autowired
    private UserRepo userRepo;
    @Override
    public List<ShoeDTO> getAll() {
        return shoeRepo.findAll().stream()
                .map(shoeDTOMapper)
                .collect(Collectors.toList());

    }

    @Override
    public List<Shoe> search(String keyword) {
        return null;
    }
}
