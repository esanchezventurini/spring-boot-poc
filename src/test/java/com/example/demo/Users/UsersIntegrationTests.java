/*package com.example.demo.Users;

import com.example.demo.Model.Gender;
import com.example.demo.Model.User;
import com.example.demo.clientproxy.UserControllerV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.ws.rs.NotFoundException;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class UsersIntegrationTests {

	@Autowired
	private UserControllerV1 proxy;

	private final User user;
	private final UUID userUid;
	private User otherUser;
	private UUID otherUserUid;
	private User otherMaleUser;
	private UUID otherMaleUserUid;

	UsersIntegrationTests() {

		userUid = UUID.fromString("0f14d0ab-9605-4a62-a9e4-5ed26688389b");
		user = new User(userUid,
				"Juan",
				"Carlos",
				Gender.MALE,
				30,
				"juancarlos@gmail.com");
	}

	@BeforeEach
	void init() {
		otherUserUid = UUID.randomUUID();
		otherUser = new User(otherUserUid,
				"Juana",
				"Fernandez",
				Gender.FEMALE,
				35,
				"juanafernandez@hotmail.com");

		otherMaleUserUid = UUID.randomUUID();
		otherMaleUser = new User(otherMaleUserUid,
				"Julian",
				"Alvarez",
				Gender.MALE,
				21,
				"julianalvarez@hotmail.com");
	}

	@Test
	void shouldGetAllUsers() {
		var users = proxy.getUsers(null);

		assertTrue(users.stream().anyMatch(x -> x.getId().equals(userUid)));
	}

	@Test
	void shouldGetUser() {
		var gotUser = proxy.getUser(userUid);

		assertThat(gotUser).usingRecursiveComparison()
				.isEqualTo(user);
	}

	@Test
	void shouldInsertUser() {
		var oldUserCount = getUsersSize();

		proxy.addUser(otherUser);

		var gotUser = proxy.getUser(otherUserUid);
		var usersCount = getUsersSize();

		assertThat(gotUser).usingRecursiveComparison()
				.isEqualTo(otherUser);

		assertThat(usersCount).isEqualTo(oldUserCount + 1);
	}

	private int getUsersSize() {
		return proxy.getUsers(null).size();
	}

	@Test
	void shouldDeleteUser() {
		proxy.addUser(otherUser);

		var initialUsersCount = getUsersSize();

		proxy.deleteUser(otherUserUid);

		var usersCount = getUsersSize();

		assertThatThrownBy(() -> proxy.getUser(otherUserUid))
				.isInstanceOf(NotFoundException.class);

		assertThat(usersCount).isEqualTo(initialUsersCount - 1);
	}

	@Test
	void shouldGetUsersByGender() {
		proxy.addUser(otherUser);
		proxy.addUser(otherMaleUser);

		var femaleUsers = proxy.getUsers(Gender.FEMALE);

		assertTrue(femaleUsers.stream().anyMatch(x -> x.getId().equals(otherUserUid)));
		assertFalse(femaleUsers.stream().anyMatch(x -> x.getId().equals(otherMaleUserUid)));
	}
}
*/