package com.solvd.twitter.web.mapper;

import com.solvd.twitter.domain.user.Post;
import com.solvd.twitter.web.dto.PostDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostDto toDto(Post post);

    Post toEntity(PostDto postDto);

    List<PostDto> toDto(List<Post> posts);

}
