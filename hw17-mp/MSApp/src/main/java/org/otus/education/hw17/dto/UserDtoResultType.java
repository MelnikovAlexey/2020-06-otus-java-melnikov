package org.otus.education.hw17.dto;

import ru.otus.messagesystem.client.ResultDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

public class UserDtoResultType extends ResultDataType {
    private static final long serialVersionUID = 1L;
    private final UserDto userDto;
    private final List<UserDto> userList;

    public UserDtoResultType(UserDto userDto, List<UserDto> userList) {
        this.userDto = userDto;
        this.userList = Objects.nonNull(userList) ? userList : null;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public List<UserDto> getUserList() {
        if (nonNull(userList))
            return new ArrayList<>(userList);
        return null;
    }

    public static UserDtoResultType getList(List<UserDto> userList) {
        return new UserDtoResultType(null, userList);
    }

    public static UserDtoResultType getSingle(UserDto dto) {
        return new UserDtoResultType(dto, null);
    }

}
