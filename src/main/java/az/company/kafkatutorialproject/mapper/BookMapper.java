package az.company.kafkatutorialproject.mapper;

import az.company.kafkatutorialproject.entity.Book;
import az.company.kafkatutorialproject.model.BookDto;
import az.company.kafkatutorialproject.model.request.BookUpdateRequest;
import az.company.kafkatutorialproject.model.request.CreateBookRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto mapToDto(Book book);

    @Mapping(target = "id", ignore = true)
    Book mapToEntity(BookDto bookDto);

    @Mapping(target = "id", ignore = true)
    Book mapRequestToEntity(CreateBookRequest request);

    @Mapping(target = "id", ignore = true)
    Book mapUpdateRequestToEntity(@MappingTarget Book book, BookUpdateRequest request);

}
