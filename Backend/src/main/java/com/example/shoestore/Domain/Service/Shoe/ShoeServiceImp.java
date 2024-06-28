package com.example.shoestore.Domain.Service.Shoe;


import com.example.shoestore.Domain.Model.Shoe.Shoe;
import com.example.shoestore.Persistence.Repository.ShoeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoeServiceImp implements ShoeService {

    private final ShoeRepository shoeRepository;

    @Override
    public List<Shoe> getAll() {
        return shoeRepository.findAll();
    }
    @Override
    public Shoe createShoe(Shoe shoe) {
        return shoeRepository.save(shoe);
    }
    @Override
    public void deleteShoe(String id) {
        shoeRepository.deleteById(id);
    }
    @Override
    public Shoe updateShoe(String id, Shoe shoeDetails) {
        Shoe shoe =updateShoeFields(id, shoeDetails);
        return shoeRepository.save(shoe);
    }
    @Override
    public Shoe getShoeById(String id) {
        return shoeRepository.findById(id).orElseThrow(() -> new RuntimeException("Shoe not found"));
    }

    @Override
    public Shoe findByInventoryId(String inventoryItemId) {
        return shoeRepository.findByInventoryId(inventoryItemId);
    }

    @Override
    public List<Shoe> search(String keyword) {
        return shoeRepository.findByNameContainingIgnoreCase(keyword);
    }
    private Shoe updateShoeFields(String id, Shoe shoeDetails)
    {
        Shoe shoe = getShoeById(id);
        shoe.setName(shoeDetails.getName());
        shoe.setDescription(shoeDetails.getDescription());
        shoe.setBrand(shoeDetails.getBrand());
        shoe.setCategory(shoeDetails.getCategory());
        shoe.setGender(shoeDetails.getGender());
        shoe.setSize(shoeDetails.getSize());
        shoe.setImage(shoeDetails.getImage());
        shoe.setPrice(shoeDetails.getPrice());
        shoe.setInventory(shoeDetails.getInventory());
        return shoe;
    }

}
