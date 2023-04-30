import React, { useEffect } from "react";

// Define a type for the component's props
interface ChangeTitleProps {
  newTitle: string;
}

const ChangeTitle: React.FC<ChangeTitleProps> = ({ newTitle }) => {
  useEffect(() => {
    const originalTitle = document.title;
    document.title = newTitle;

    return () => {
      document.title = originalTitle;
    };
  }, [newTitle]);

  return null;
};

export default ChangeTitle;
