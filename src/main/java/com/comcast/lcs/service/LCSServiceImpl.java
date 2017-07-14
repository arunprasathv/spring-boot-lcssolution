package com.comcast.lcs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.comcast.lcs.messages.LCSErrorMessages;
import com.comcast.lcs.request.LCSRequest;
import com.comcast.lcs.request.LCSValue;
import com.comcast.lcs.response.LCSResponse;

@Service
public class LCSServiceImpl implements LCSService {
	private String[] values;
	private int shortestStringIndex;
	@Override
	public LCSResponse findLCS(LCSRequest lcsRequest) {
		List<LCSValue> lcsList = new ArrayList<>();
	    // <summary>
        // This algorithm works at O(n*m2) time complexity
        // Logic - One by one consider all substrings of shortest string and for every substring check if it is a substring on the list of string other than the shortest string
        // </summary>
        // <returns></returns>
		List<LCSValue> lcsValuesList= lcsRequest.getValues();
		values = new String[lcsValuesList.size()];
		int i=0;
		for(LCSValue obj:lcsValuesList){
			values[i]=obj.getValue();
			i++;
		}
		AssignShortestStringIndex();
        boolean isFound = false;
        LCSResponse lcsResponse = new LCSResponse();
        //Take the shortest string from the list.
        String shortestString = values[shortestStringIndex];
        LCSValue lcsVal=null;
        List<String> lcsStrList = new ArrayList<>();
        List<String> notMatchList = new ArrayList<>();
        //Take the length from the shortest string and check the string list whether if the every substring is found or not. If not found, decrease the length by 1 and check again to the string list.
        for (int targetStringLength = shortestString.length(); targetStringLength >= 0; targetStringLength--)
        {
            //Check all the combinations for the shortest string length.
            for (int startIndex = 0; (startIndex + targetStringLength) <= shortestString.length(); startIndex++)
            {
                String currentSubString = mySubString(shortestString,startIndex,targetStringLength);
                if ((!lcsStrList.contains(currentSubString)) && (!notMatchList.contains(currentSubString) && !StringUtils.isEmpty(currentSubString))){
                    if (IsSubstringFoundInTheList(currentSubString))
                    {
                    	lcsStrList.add(currentSubString);
                        isFound = true; 
                        lcsVal = new LCSValue();
                        lcsVal.setValue(currentSubString);
                        lcsList.add(lcsVal);
                    }else
                    {
                        notMatchList.add(currentSubString);
                    }
                }
            }
            //If string is found in the string list then we no need to check again by decrease its length, because we found maximum length of the string.
            if (isFound){
                break;
            }
        }
        lcsResponse.setValues(lcsList);
        return lcsResponse;
    }

	 /// <summary>
    /// Works for two strings only, works at O(n*m) time compexity
    /// </summary>
    /// <param name="lcsRequest"></param>
    /// <returns></returns>
    public LCSResponse dynamicLCS(LCSRequest lcsRequest)
    {
    	LCSValue lcsVal=null;
    	LCSResponse response = new LCSResponse();
    	List<LCSValue> retList =new ArrayList<>();
        try
        {
            if (lcsRequest != null && lcsRequest.getValues()!=null)
            {
                String str1 = lcsRequest.getValues().get(0).getValue();
                String str2 = lcsRequest.getValues().get(1).getValue();
                String lcs= FindLongestCommonSubstring(str1.toCharArray(), str2.toCharArray());
                lcsVal = new LCSValue();
                lcsVal.setValue(lcs);
                retList.add(lcsVal);
                response.setValues(retList);
                return response;
            }
            return response;
        }catch (Exception ex){
        	lcsVal = new LCSValue();
        	lcsVal.setValue(LCSErrorMessages.ErrorProcessingRequest);
        	retList =new ArrayList<LCSValue>();
            retList.add(lcsVal);
            response.setValues(retList);
            return response;
        }
    }
	// Is given String found in the String list(either as whole String or
	// substring)
	private boolean IsSubstringFoundInTheList(String subString) {
		boolean isFound = true;
		for (int index = 0; index < values.length; index++) {
			if (index != shortestStringIndex) {
				if (values[index].indexOf(subString) < 0) {
					isFound = false;
					break;
				}
			}
		}
		return isFound;
	}

	// <summary>
	// Find shortest String index from the String list.
	// </summary>
	private void AssignShortestStringIndex() {
		shortestStringIndex = 0;
		for (int index = 1; index < values.length; index++) {
			if (values[index].length() < values[shortestStringIndex].length()) {
				shortestStringIndex = index;
			}
		}
	}

	// <summary>
	// This alogorithm works at O(n*m) time complexity
	// Logic -
	// </summary>
	// <param name="str1"></param>
	// <param name="str2"></param>
	public String FindLongestCommonSubstring(char[] str1, char[] str2)
    {
        int[][] l = new int[str1.length][str2.length];
        int lcs = -1;
        String lcsSubstr = "";
        int end = -1;

        for (int i = 0; i < str1.length; i++)
        {
            for (int j = 0; j < str2.length; j++)
            {
                if (str1[i] == str2[j])
                {
                    if (i == 0 || j == 0)
                    {
                        l[i][j] = 1; // this should be always 1 as there's no previous value to look from 0th position
                    }
                    else
                        l[i][j] = l[i - 1][j - 1] + 1; //sum up the values diagonlly to know the longest substring
                    if (l[i][j] > lcs)
                    {
                        lcs = l[i][j]; //to get the length of the LCS and will help to identify the start position of the substring
                        end = i; //to know the end position of the substring from the first given string, Note -  the substring can't go beyond this position
                    }
                }
                else
                    l[i][j] = 0;
            }
        }
        //end-lcs+1 will give the start position of the substring
        for (int i = end - lcs + 1; i <= end; i++){
            lcsSubstr += str1[i];
        }

        return lcsSubstr;
    }
	private String mySubString(String myString, int start, int length) {
	    return myString.substring(start, Math.min(start + length, myString.length()));
	}
}
