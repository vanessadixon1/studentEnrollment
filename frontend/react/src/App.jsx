import { useState, useRef, useEffect } from 'react'
import { Wrap,WrapItem,Spinner,Text } from '@chakra-ui/react'
import SidebarWithHeader from "./components/shared/SideBar.jsx";

import {getStudents} from "./services/client.js";
import CardWithImage from "./components/Card";

function App() {
    const [isLoading, setIsLoading] = useState(false)
    const [students, setStudents] = useState([])

    useEffect(() => {
        setIsLoading(true)
        setTimeout(() => {
            getStudents().then(res => {
                setStudents(res.data)
            }).catch(err => {
                console.log(err)
            }).finally(() => {
                setIsLoading(false)
            })
        },3000)

    },[])

    if(isLoading) {
        return (
            <SidebarWithHeader>
                <Spinner thickness='4px' speed='0.65s' emptyColor='gray.200' color='blue.500' size='xl'/>
            </SidebarWithHeader>
        )
    }

    if(students.length < 1) {
        return (
            <SidebarWithHeader>
                <Text>No students available</Text>
            </SidebarWithHeader>
        )
    }

  return (
          <SidebarWithHeader>
              <Wrap justify={"center"} spacing={"30px"}>
                  {students.map(({id, firstName, lastName, email, age},idx) => (
                      <WrapItem key={id}>
                          <CardWithImage name={`${firstName} ${lastName}`} email={email} age={age}/>
                      </WrapItem>
                      ))}
              </Wrap>
          </SidebarWithHeader>
  )
}

export default App
