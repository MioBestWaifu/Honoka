using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Hestia.Scripts
{
    public class UserInformation
    {
        string email, username, password, description;
        int id;
        DateTime birthday;

        public string Email { get => email; set => email = value; }
        public string Password { get => password; set => password = value; }
        public string Username { get => username; set => username = value; }
        public string Description { get => description; set => description = value; }
        public DateTime Birthday { get => birthday; set => birthday = value; }
        public int Id { get => id; set => id = value; }
        
        public UserInformation()
        {

        }
        public UserInformation(int id)
        {
            this.id = id;
        }

        public UserInformation(string email, string password)
        {
            this.email = email;
            this.password = password;
        }

        public UserInformation(string email, string username, string password, string description, DateTime birthday) : this(email, username)
        {
            this.password = password;
            this.description = description;
            this.birthday = birthday;
        }
    }
}
