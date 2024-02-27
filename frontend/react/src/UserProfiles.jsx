import UserProfile from "./UserProfile.jsx";

const UserProfiles = ({data}) => {

    return (
        <div>
            {data.map(user => {
                let ran_imageNum = Math.floor(Math.random() * 100)
                return <UserProfile key={user.id} name={user.name} age={user.age} gender={user.gender} imageNumber={ran_imageNum}/>
            })}
        </div>
    )
}

export default UserProfiles