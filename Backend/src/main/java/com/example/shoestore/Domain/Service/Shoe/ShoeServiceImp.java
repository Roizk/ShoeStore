package com.example.shoestore.Domain.Service.Shoe;


import com.example.shoestore.Domain.DTO.ShoeDTO;
import com.example.shoestore.Domain.DTO.ShoeDTOMapper;
import com.example.shoestore.Domain.Model.Shoe.Shoe;
import com.example.shoestore.Persistence.Repository.ShoeRepository;
import com.example.shoestore.Persistence.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoeServiceImp implements ShoeService {
    @Autowired
    private ShoeRepository shoeRepository;
    private ShoeDTOMapper shoeDTOMapper;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<ShoeDTO> getAll() {
        return shoeRepository.findAll().stream()
                .map(shoeDTOMapper)
                .collect(Collectors.toList());

    }

    @Override
    public List<Shoe> search(String keyword) {
        return null;
    }
}
