INSERT INTO users (
  username,
  email,
  password_hash,
  first_name,
  last_name,
  birthdate,
  address
) VALUES
  (
    'alice123',
    'alice@example.com',
    '$2a$10$DowJ8uFQbN7lYhKk8JZRee1QoXbh6xM6P7RzYQG8/Za1Xl5NaLc9O',  -- BCrypt("password1")
    'Alice',
    'Anderson',
    '1990-01-15',
    '123 Main St'
  ),
  (
    'bob456',
    'bob@example.com',
    '$2a$10$u4rXIYk5kZ9tD0YTW0nQheOqZpZGCk5Y.BEO1HDuDpGbTAwHj8v7C',  -- BCrypt("password2")
    'Bob',
    'Baker',
    '1985-06-20',
    '456 Oak Ave'
  ),
  (
    'dude',
    'dude@example.com',
    '$2a$10$7Yk5Z2xVfH9dJ0lQpR3tOu1LmN5BaC4XyZ0WbH1gIjKlMnOpQrStU',  -- BCrypt("password3")
    'The',
    'Dude',
    '1990-05-05',
    '789 Elm St'
  );
